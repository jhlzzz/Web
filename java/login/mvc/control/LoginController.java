package login.mvc.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.mvc.model.LoginService;
import mvc.domain.Member;

import static login.mvc.model.LoginConst.*;

@WebServlet("/login/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch (m) {
				case "form": form(request, response); break;
				case "check": check(request, response); break;
				case "logout": logout(request, response); break;
			}
		}else {
			response.sendRedirect("../");
		}
	}
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "login.jsp";
		response.sendRedirect(view);
	}
	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		//유효성 검사( 클라이언트측View:js , 서버측Contoller:java )
		System.out.println("email: "+ email + ", pwd: " + pwd);
		
		LoginService service = LoginService.getInstance();
		int result = service.check(email, pwd);
		System.out.println("result: " + result);
		
		if(result == YES_ID_PWD) {
			Member m = service.getMemberS(email);
			HttpSession session = request.getSession();
			session.setAttribute("loginOkUser", m);
		}
		request.setAttribute("result", result);//jsp에서 팝업창 메세지 띄우려고,,
		
		String view = "msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//session.removeAttribute("loginOkUser");
		session.invalidate();
		
		response.sendRedirect("../");
	}
}
