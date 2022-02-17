package land.mvc.model;

import static jeju.all.consts.BoardConst.*;

import java.util.ArrayList;

import land.mvc.domain.Landmark;
public class LandService {

	private LandRepository dao;
	private static final LandService instance = new LandService();
	private LandService() {
		dao = new LandRepository();
	}
	
	public static LandService getInstance() {
		return instance;
	}
	
	public ArrayList<Landmark> listS(long ps, long cp){
		  long psTemp = -1;
	      long cpTemp = -1;
	      
	      if(ps == -1) psTemp = PAGE;
	      else psTemp = ps;
	      if(cp == -1) cpTemp = 1;
	      else cpTemp = cp;
	      
	      long min = psTemp*(cpTemp-1);
	      long max = psTemp*cpTemp;
		
		return dao.list(max, min);
	}
	
	public void deleteS(int lnum) {
		dao.delete(lnum);
	}
	
	public Landmark contentS(int lnum){
		return dao.content(lnum);
	}
	
	public long boardCountS() {
	      return dao.boardCount();
	   }
	public ArrayList<Long> getCpList(long ps, long cp, long maxPage){
	      ArrayList<Long> cpList = new ArrayList<Long>();
		      long loop = -1;
		      long start = 1;
		      long psTemp = -1;
		      long cpTemp = -1;
		      long maxPageTemp = -1;
		      
		      if(ps == -1) psTemp = PAGE;
		      else psTemp = ps;
		      
		      if(cp == -1) cpTemp = 0;
		      else cpTemp = cp;
		      
		      if(maxPage < 1) maxPageTemp = 1;
		      else maxPageTemp = maxPage;
		      
		      if(cpTemp/psTemp != 0) {
		         loop = cpTemp/psTemp+1;
		         if(psTemp*(loop-1) == cpTemp){
		            start = psTemp*(loop-2)+1;
		            loop -= 1;
		         }else{
		            start = psTemp*(loop-1)+1;
		         }
		      }else {
		         loop = 1;
		      }
		      for(long i=start; i<=psTemp*loop; i++) {
		         if(i > maxPageTemp) break;
		         cpList.add(i);
		      }
		      return cpList;
		   }
	   public long getMaxPage(long ps) {
	      long maxPage = -1;
	      long psTemp = -1;
	      if(ps == -1) {
	         psTemp = PAGE;
	      }else {
	         psTemp = ps;
	      }
	      long boardCount = boardCountS();
	      maxPage = boardCount/psTemp;
	      if(boardCount != psTemp*maxPage) {
	         maxPage = boardCount/psTemp+1;
	      }
	      return maxPage;
	   }
}
