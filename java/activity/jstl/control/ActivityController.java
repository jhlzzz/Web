package activity.jstl.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import activity.mvc.model.ActivityService;

import activity.mvc.domain.Activity;



@WebServlet("/activity/activity.do")
public class ActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ActivityService service = ActivityService.getInstance();
	
		
		 long ps = getPs(request);
	     long cp = getCp(request);
	     long maxPage = service.getMaxPage(ps);
	     long size = service.boardCountS();
	     ArrayList<Long> cpList = service.getCpList(ps, cp, maxPage);
	 	 ArrayList<Activity> list = service.listS(ps,cp);
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
	
	private int getAnum(HttpServletRequest request){
        int anum = -1;
		String anumStr = request.getParameter("anum");
		if(anumStr != null){
			anumStr = anumStr.trim();
			if(anumStr.length() != 0){
				try{
					anum = Integer.parseInt(anumStr); 
					return anum;
				}catch(NumberFormatException ne){
				}
			}
		}
		return anum;
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ActivityService service = ActivityService.getInstance();	
		int anum = getAnum(request);
		service.deleteS(anum);

		response.sendRedirect("activity.do");
	}
	
	private void content(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			int anum = getAnum(request);
			ActivityService service = ActivityService.getInstance();
			Activity activity = service.contentS(anum);
			request.setAttribute("activity", activity);
			
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


