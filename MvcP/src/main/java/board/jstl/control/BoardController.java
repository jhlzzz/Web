package board.jstl.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.mvc.model.BoardService;
import mvc.domain.Board;

@WebServlet("/board/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch (m) {
			case "list": list(request, response); break;
			case "input": input(request, response); break;
			case "insert": insert(request, response); break;
			case "list_con": list_con(request, response); break;
			case "update_update": update_update(request, response); break;
			case "update_list": update_list(request, response); break;
			case "del": del(request, response); break;
			}
		}else {
			list(request, response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		ArrayList<Board> list = service.listS();
		request.setAttribute("list", list);
		
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void input(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("input.jsp");
	}
	private void list_con(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int seq = getSeq(request);
		BoardService service = BoardService.getInstance();
		ArrayList<Board> list_con = service.list_conS(seq);
		request.setAttribute("list_con", list_con);
		
		String view = "content.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);	
	}
	private void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. Model 핸들링(java)
		BoardService service = BoardService.getInstance();
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		Board dto = new Board(-1, writer, email, subject, content, null);
		boolean flag = service.insertS(dto);
		request.setAttribute("flag", flag);
		
		//2. View 지정(jsp)
		String view = "insert.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void update_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int seq = getSeq(request);
		BoardService service = BoardService.getInstance();
		ArrayList<Board> update_list = service.update_listS(seq);
		request.setAttribute("update_list", update_list);
		
		String view = "update_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);	
	}
	private void update_update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int seq = getSeq(request);
		BoardService service = BoardService.getInstance();
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		Board dto = new Board(seq, null, email, subject, content, null);
		service.update_updateS(dto);
		
		response.sendRedirect("board.do");
	}
	private void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		int seq = getSeq(request);
		service.deleteS(seq);
		
		response.sendRedirect("board.do");
	}
	private int getSeq(HttpServletRequest request){
        int seq = -1;
		String seqStr = request.getParameter("seq");
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr); 
					return seq;
				}catch(NumberFormatException ne){
				}
			}
		}
		return seq;
	}
}
