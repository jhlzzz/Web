package jeju.board.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import jeju.board.domain.Board;
import jeju.board.model.BoardService;
import static jeju.file.model.FileSet.*;


@WebServlet("/jeju_board/jeju_board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		if(message != null) {
			message.trim();
			switch(message) {
				case "mainBoard": mainBoard(request, response); break;
				case "input": input(request, response); break;
				case "index": index(request, response); break;
				case "insert": insert(request, response); break;
				case "delete": delete(request, response); break;
				case "updateList": updateList(request, response); break;
				case "update": update(request, response); break;
				case "loveHateUpdate": loveHateUpdate(request, response); break;
				case "content": content(request, response); break;
				default: mainBoard(request, response); break;
			}
		}else {
			mainBoard(request, response);
		}
	}
	private void mainBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		BoardService service = BoardService.getInstance();
		long ps = getPs(request);
		long cp = getCp(request);
		long maxPage = service.getMaxPage(ps);
		long size = service.boardCountS();
		ArrayList<Long> cpList = service.getCpList(ps, cp, maxPage);
		ArrayList<Board> listAll = service.listAllS(ps, cp);
		request.setAttribute("listAll", listAll);
		request.setAttribute("ps", ps);
		request.setAttribute("size", size);
		request.setAttribute("cp", cp);
		request.setAttribute("cpList", cpList);
		request.setAttribute("maxPage", maxPage);
		
		String view = "main_board.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void input(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("input.jsp");
	}
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("../");
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Board board = new Board();
		BoardService service = BoardService.getInstance();
		MultipartRequest mr = fileUpload(request, board);
		board = getMrParameterAll(request, mr, board);
		boolean flag = false;

		if(board.getNick() != null && board.getSubject() != null && board.getKategorie() != null && board.getTag() != null && board.getContent() != null && board.getDivision() != -1) {
			
			flag = service.insertS(board);
			request.setAttribute("flag", flag);
		}else {
			request.setAttribute("flag", flag);
		}
		
		String view = "insert.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void content(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		BoardService service = BoardService.getInstance();
		long bnum = getBnum(request);
		long views = service.viewsCheckS(bnum);
		service.viewsUpdateS(bnum, views);
		Board board = service.contentS(bnum);
		request.setAttribute("board", board);
		
		String view = "board_content.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		BoardService service = BoardService.getInstance();
		long bnum = getBnum(request);
		service.deleteS(bnum);
		
		response.sendRedirect("jeju_board.do");
	}
	private void updateList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		BoardService service = BoardService.getInstance();
		long bnum = getBnum(request);
		Board board = service.contentS(bnum);
		request.setAttribute("board", board);
		
		String view = "update.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Board board = new Board();
		BoardService service = BoardService.getInstance();
		MultipartRequest mr = fileUpload(request, board);
		board = getMrParameterAll(request, mr, board);
		service.updateS(board);
		
		response.sendRedirect("jeju_board.do");
	}
	private void loveHateUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		BoardService service = BoardService.getInstance();
		String set = getSet(request);
		String div = getDiv(request);
		long bnum = getBnum(request);
		if(set == null || div == null || bnum == -1) {
			response.sendRedirect("jeju_board.do?message=mainBoard");
		}
		System.out.println("set: " + set);
		if(div.equals("love")) {
			long love = service.loveCheckS(bnum);
			if(set.equals("up")) {
				service.loveUpS(bnum, love);
			}else if(set.equals("down")) {
				service.loveDownS(bnum, love);
			}else {
				response.sendRedirect("jeju_board.do?message=mainBoard");
			}
		}else if(div.equals("hate")) {
			long hate = service.hateCheckS(bnum);
			if(set.equals("up")) {
				service.hateUpS(bnum, hate);
			}else if(set.equals("down")) {
				service.hateDownS(bnum, hate);
			}else {
				response.sendRedirect("jeju_board.do?message=mainBoard");
			}
		}else {
			response.sendRedirect("jeju_board.do?message=mainBoard");
		}
		Board board = service.contentS(bnum);
		request.setAttribute("board", board);
		
		String view = "jeju_board.do?message=content&bnum="+bnum;
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	private String getDiv(HttpServletRequest request) {
		String div = request.getParameter("div");
		if(div != null) {
			div = div.trim();
			if(div.length() != 0) {
				return div;
			}
		}
		return null;
	}
	private String getSet(HttpServletRequest request) {
		String set = request.getParameter("set");
		if(set != null) {
			set = set.trim();
			if(set.length() != 0) {
				return set;
			}
		}
		return null;
	}
	private long getBnum(HttpServletRequest request) {
		String bnumStr = request.getParameter("bnum");
		long bnum = -1;
		if(bnumStr != null) {
			bnumStr = bnumStr.trim();
			if(bnumStr.length() != 0) {
				try {
					bnum = Long.parseLong(bnumStr);
					return bnum;
				}catch(NumberFormatException nfe) {
				}
			}
		}
		return bnum;
	}
	
	private Board getMrParameterAll(HttpServletRequest request, MultipartRequest mr, Board board) {
		String bnumStr = mr.getParameter("bnum");
		String nick = mr.getParameter("nick");
		String subject = mr.getParameter("subject");
		String kategorie = mr.getParameter("kategorie");
		String tag = mr.getParameter("tag");
		String content = mr.getParameter("content");
		String loveStr = mr.getParameter("love");
		String hateStr = mr.getParameter("hate");
		String viewsStr = mr.getParameter("views");
		String divisionStr = mr.getParameter("division");
		
		
		if(bnumStr != null) {
			bnumStr = bnumStr.trim();
			if(bnumStr.length() != 0) {
				try {
					long bnum = Long.parseLong(bnumStr);
					board.setBnum(bnum);
				}catch(NumberFormatException nfe) {
					board.setBnum(-1);
				}
			}else {
				board.setBnum(-1);
			}
		}else {
			board.setBnum(-1);
		}
		if(nick != null) {
			nick = nick.trim();
			if(nick.length() != 0) {
				board.setNick(nick);
			}
		}
		if(subject != null) {
			subject = subject.trim();
			if(subject.length() != 0) {
				board.setSubject(subject);
			}
		}
		if(kategorie != null) {
			kategorie = kategorie.trim();
			if(kategorie.length() != 0) {
				board.setKategorie(kategorie);
			}
		}
		if(tag != null) {
			tag = tag.trim();
			if(tag.length() != 0) {
				board.setTag(tag);
			}
		}
		if(content != null) {
			content = content.trim();
			if(content.length() != 0) {
				board.setContent(content);
			}
		}
		if(loveStr != null) {
			loveStr = loveStr.trim();
			if(loveStr.length() != 0) {
				try {
					long love = Long.parseLong(loveStr);
					board.setLove(love);
				}catch(NumberFormatException nfe) {
					board.setLove(-1);
				}
			}else {
				board.setLove(-1);
			}
		}else {
			board.setLove(-1);
		}
		if(hateStr != null) {
			hateStr = hateStr.trim();
			if(hateStr.length() != 0) {
				try {
					long hate = Long.parseLong(hateStr);
					board.setHate(hate);
				}catch(NumberFormatException nfe) {
					board.setHate(-1);
				}
			}else {
				board.setHate(-1);
			}
		}else {
			board.setHate(-1);
		}
		if(viewsStr != null) {
			viewsStr = viewsStr.trim();
			if(viewsStr.length() != 0) {
				try {
					long views = Long.parseLong(viewsStr);
					board.setViews(views);
				}catch(NumberFormatException nfe) {
					board.setViews(-1);
				}
			}else {
				board.setViews(-1);
			}
		}else {
			board.setViews(-1);
		}
		if(divisionStr != null) {
			divisionStr = divisionStr.trim();
			if(divisionStr.length() != 0) {
				try {
					int division = Integer.parseInt(divisionStr);
					board.setDivision(division);
				}catch(NumberFormatException nfe) {
					board.setDivision(-1);
				}
			}else {
				board.setDivision(-1);
			}
		}else {
			board.setDivision(-1);
		}
		return board;
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
	private MultipartRequest fileUpload(HttpServletRequest request, Board board) throws ServletException, IOException{
				String saveDir = FILE_DIR;
				File fSaveDir = new File(saveDir);
				if(!fSaveDir.exists()) fSaveDir.mkdirs();
				
				int maxPostSize = 1*1024*1024;
				FileRenamePolicy policy = new DefaultFileRenamePolicy();
				MultipartRequest mr = null;
				try {
					mr = new MultipartRequest(request, saveDir, maxPostSize, "utf-8", policy);
				}catch(IOException ie) {
					System.out.println("업로드 실패: " + ie);
				}
				String fname = mr.getFilesystemName("photoName");
				String ofname = mr.getOriginalFileName("photoName");
				board.setBphoto(fname);
				board.setBoriphoto(ofname);
				System.out.println("업로드된파일정보: fname: " + fname + ", ofname: " + ofname);
				
				return mr;
	}
	private void fileDownload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String saveDir = FILE_DIR;
		String fname = request.getParameter("fname");
		File f = new File(saveDir, fname);
		
		response.setContentType("application/octet-stream"); 
		String Agent=request.getHeader("USER-AGENT");
		if( Agent.indexOf("MSIE") >= 0 ){
			int i = Agent.indexOf( 'M', 2 );
			String IEV = Agent.substring( i + 5, i + 8 );
			if( IEV.equalsIgnoreCase("5.5") ){
				response.setHeader("Content-Disposition", "filename=" + new String( fname.getBytes("utf-8"), "8859_1") );
			}else{
				response.setHeader("Content-Disposition", "attachment;filename="+new String(fname.getBytes("utf-8"),"8859_1"));
			}
		}else{
			response.setHeader("Content-Disposition", "attachment;filename=" + new String( fname.getBytes("utf-8"), "8859_1") );
		}
		
		if(f.exists() && f.isFile()) {
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			OutputStream os = null;
			BufferedOutputStream bos = null;
			try {
				fis = new FileInputStream(f); //Data Source (서버측 파일)
				bis = new BufferedInputStream(fis, 2048);
				os = response.getOutputStream(); //Data Destination ( 클라이언트 브라우져 )
				bos = new BufferedOutputStream(os, 2048);
				
				int count = 0;
				byte b[] = new byte[1024];
				while((count = bis.read(b)) != -1) {
					bos.write(b, 0, count);
				}
				bos.flush();
			}catch(IOException ie) {		
			}finally {
				try {
					if(bos != null) bos.close();
					if(bis != null) bis.close();
					if(fis != null) fis.close();
					if(os != null) os.close();
				}catch(IOException ie) {}
			}
		}
	}
}
