package land.jstl.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import land.mvc.model.LandService;
import land.mvc.domain.Landmark;

/**
 * Servlet implementation class LandController
 */
@WebServlet("/land/land.do")
public class LandController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch(m) {
			case "index": index(request, response); break;
			case "list": list(request, response); break;
			case "delete": delete(request, response); break;
			case "content": content(request, response); break;
			}
		}else {
			list(request, response);
		}
	}
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("../");
	}	
	private void list(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			
			LandService service = LandService.getInstance();

			
			 long ps = getPs(request);
		     long cp = getCp(request);
		     long maxPage = service.getMaxPage(ps);
		     long size = service.boardCountS();
		     ArrayList<Long> cpList = service.getCpList(ps, cp, maxPage);
			 ArrayList<Landmark> list = service.listS(ps,cp);
			 request.setAttribute("list", list);
		     request.setAttribute("ps", ps);
		     request.setAttribute("size", size);
		     request.setAttribute("cp", cp);
		     request.setAttribute("cpList", cpList);
		     request.setAttribute("maxPage", maxPage);
		    String view = "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
			
		}
		private int getLnum(HttpServletRequest request){
	        int lnum = -1;
			String lnumStr = request.getParameter("lnum");
			if(lnumStr != null){
				lnumStr = lnumStr.trim();
				if(lnumStr.length() != 0){
					try{
						lnum = Integer.parseInt(lnumStr); 
						return lnum;
					}catch(NumberFormatException ne){
					}
				}
			}
			return lnum;
		}
		private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			LandService service = LandService.getInstance();	
			int lnum = getLnum(request);
			service.deleteS(lnum);

			response.sendRedirect("land.do");
		}
		
		private void content(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			int lnum = getLnum(request);
			LandService service = LandService.getInstance();
			Landmark landmark = service.contentS(lnum);
			request.setAttribute("landmark", landmark);
			
			String view = "content.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		
		private long getPs(HttpServletRequest request){
		      long ps = -1;
		      String psStr = request.getParameter("ps");
		      if(psStr != null){
		         psStr = psStr.trim();
		         if(psStr.length() != 0){
		            try{
		               ps = Integer.parseInt(psStr);
		               return ps;
		            }catch(NumberFormatException nfe){
		            }
		         }
		      }
		      return ps;
		   }
		   private long getCp(HttpServletRequest request){
		      long cp = -1;
		      String cpStr = request.getParameter("cp");
		      if(cpStr != null){
		         cpStr = cpStr.trim();
		         if(cpStr.length() != 0){
		            try{
		               cp = Integer.parseInt(cpStr);
		               return cp;
		            }catch(NumberFormatException nfe){
		            }
		         }
		      }
		      return cp;
		   }
}
