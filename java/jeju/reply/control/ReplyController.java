package jeju.reply.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReplyController
 */
@WebServlet("/jeju_reply/jeju_reply.do")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		if(message != null) {
			message.trim();
			switch(message){
				case "index": index(request, response); break;
				case "board": board(request, response); break;
				case "food": food(request, response); break;
				case "activity": activity(request, response); break;
				case "hotel": hotel(request, response); break;
				case "landmark": landmark(request, response); break;
				case "course": course(request, response); break;
				default: index(request, response);
			}
		}else {
			index(request, response);
		}
	}
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("../");
	}
	private void board(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	private void food(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

	}
	private void activity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	}
	private void hotel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	}
	private void landmark(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

	}
	private void course(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	}
}
