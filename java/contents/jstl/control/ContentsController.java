package contents.jstl.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contents/contents.do")
public class ContentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		if(message != null) {
			message = message.trim();
			switch (message) {
			case "list": list(request, response); break;
			}
		}else {
			list(request, response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String view = "contents_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
