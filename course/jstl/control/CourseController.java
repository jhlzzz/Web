package course.jstl.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.mvc.model.BoardService;
import course.jstl.model.CourseService;
import mvc.domain.Board;
import mvc.domain.Course;


@WebServlet("/course/course.do")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseMessage = request.getParameter("m");
		if(courseMessage != null) {
			courseMessage = courseMessage.trim();
			switch(courseMessage) {
				case "list" : list(request, response); break;
				case "list_con" : list_con(request, response); break;
			}
		}else {
			list(request, response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		CourseService service = CourseService.getInstance();
		String division = request.getParameter("division");
		String imageName = "jejucourse.png";
		if(division == null) {
			division = "-1";
		}else if (division.equals("1")) {
			imageName = "center.png";
		}else if (division.equals("2")) {
			imageName = "top.png";
		}else if (division.equals("3")) {
			imageName = "left.png";
		}else if (division.equals("4")) {
			imageName = "bottom.png";
		}else if (division.equals("5")) {
			imageName = "right.png";
		}
		request.setAttribute("imageName", imageName);
		ArrayList<Course> list = service.listS(division);
		request.setAttribute("list", list);
		
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void list_con(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long seq = getSeq(request);
		CourseService service = CourseService.getInstance();
		Course course = service.list_conS(seq);
		request.setAttribute("course", course);
		
		String view = "content.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);	
	}
	private long getSeq(HttpServletRequest request){
        long seq = -1;
		String seqStr = request.getParameter("seq");
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Long.parseLong(seqStr); 
					return seq;
				}catch(NumberFormatException ne){
				}
			}
		}
		return seq;
	}
}


