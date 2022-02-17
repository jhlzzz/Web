package jeju.lhcheck.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeju.lhcheck.domain.LhCheck;
import jeju.lhcheck.model.LhCheckService;
import static jeju.all.consts.ClassfiConst.*;

@WebServlet("/LhCheck/LhCheck.do")
public class LhCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		if(message != null) {
			message = message.trim();
			switch(message) {
				case "loveCheck": loveCheck(request, response); break;
				case "hateCheck": hateCheck(request, response); break;
				default: response.sendRedirect("../jeju_board/jeju_board.do?message=mainBoard");
			}
		}else {
			response.sendRedirect("../jeju_board/jeju_board.do?message=mainBoard");
		}
	}
	
	public void loveCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LhCheckService service = LhCheckService.getInstance();
		LhCheck lhcheck = getParameterAll(request);
		String nav = null;
		String set = null;
		int love = -1;
		if(lhcheck == null) {
			response.sendRedirect("../jeju_board/jeju_board.do?message=mainBoard");
		}else {
			love = service.loveCheckS(lhcheck);
			if(love == -1) {
				service.lhInsertS(lhcheck);
				service.loveUpS(lhcheck);
				set = "up";
			}else if(love == 0){
				service.loveUpS(lhcheck);
				set = "up";
			}else if(love == 1) {
				service.loveDownS(lhcheck);
				set = "down";
			}
		}
		switch(lhcheck.getClassfi()) {
			case BOARD: nav = "jeju_board/jeju_board.do?message="; break;
			case COURSE: nav = "course/course.do?message="; break;
			case ACTIVITY: nav = "activity/activity.do?m="; break;
			case FOOD: nav = "food/food.do?m="; break;
			case LANDMARK: nav = "land/land.do?m="; break;
		}
		response.sendRedirect("../"+nav+"loveHateUpdate&bnum="+lhcheck.getContentnum()+"&set="+set+"&div=love");
	}
	public void hateCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LhCheckService service = LhCheckService.getInstance();
		LhCheck lhcheck = getParameterAll(request);
		String nav = null;
		String set = null;
		if(lhcheck == null) {
			response.sendRedirect("../jeju_board/jeju_board.do?message=mainBoard");
		}else {
			int hate = service.hateCheckS(lhcheck);
			if(hate == -1) {
				service.lhInsertS(lhcheck);
				service.hateUpS(lhcheck);
				set = "up";
			}else if(hate == 0){
				service.hateUpS(lhcheck);
				set = "up";
			}else if(hate == 1) {
				service.hateDownS(lhcheck);
				set = "down";
			}
		}
		switch(lhcheck.getClassfi()) {
			case BOARD: nav = "jeju_board/jeju_board.do?message="; break;
			case COURSE: nav = "course/course.do?message="; break;
			case ACTIVITY: nav = "activity/activity.do?m="; break;
			case FOOD: nav = "food/food.do?m="; break;
			case LANDMARK: nav = "land/land.do?m="; break;
		}
		response.sendRedirect("../"+nav+"loveHateUpdate&bnum="+lhcheck.getContentnum()+"&set="+set+"&div=hate");
	}
	private LhCheck getParameterAll(HttpServletRequest request) {
		LhCheck lhcheck = new LhCheck();
		String classfiStr = request.getParameter("classFi");
		String contentnumStr = request.getParameter("contentnum");
		String nick = request.getParameter("nick");
		if(classfiStr != null && contentnumStr != null && nick != null) {
			classfiStr = classfiStr.trim();
			contentnumStr = contentnumStr.trim();
			nick = nick.trim();
			if(classfiStr.length() != 0 && contentnumStr.length() != 0 && nick.length() != 0) {
				try {
					int classfi = Integer.parseInt(classfiStr);
					long contentnum = Integer.parseInt(contentnumStr);
					lhcheck.setClassfi(classfi);
					lhcheck.setContentnum(contentnum);
					lhcheck.setNick(nick);
					return lhcheck;
				}catch(NumberFormatException nfe) {
					return lhcheck;
				}
			}
		}
		return lhcheck;
	}
}
