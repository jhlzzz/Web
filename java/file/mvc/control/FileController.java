package file.mvc.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import file.mvc.model.FileSet;


@WebServlet("/file/file.do")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch (m) {
				case "list": list(request, response); break;
				case "form": form(request, response); break;
				case "upload": upload(request, response); break;			
				case "del": del(request, response); break;			
				case "download": download(request, response); break;			
			}
		}else {
			list(request, response);
		}	
	}
	
	public void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "form.jsp";
		response.sendRedirect(view);
	}
	public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir = FileSet.FILE_DIR;
		File fSaveDir = new File(saveDir);
		if(!fSaveDir.exists()) fSaveDir.mkdirs();
		
		int maxPostSize = 1*1024*1024;
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(request, saveDir, maxPostSize, "utf-8", policy);
		}catch(IOException ie){
			System.out.println("업로드 실패: " + ie);
		}
		String writer = mr.getParameter("writer");
		String fname = mr.getFilesystemName("fname");
		String ofname = mr.getOriginalFileName("fname");
		System.out.println();
		
		String view = "file.do";
		response.sendRedirect(view);
	}
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir = FileSet.FILE_DIR;
		File fSaveDir = new File(saveDir);
		File kids[] = fSaveDir.listFiles();
		request.setAttribute("kids", kids);
		
		
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir = FileSet.FILE_DIR;
		String fname = request.getParameter("fname");
		File f = new File(saveDir, fname);
		if(f.exists()) f.delete();
		
		String view = "file.do";
		response.sendRedirect(view);
	}
	public void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir = FileSet.FILE_DIR;
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
		if(f.exists()&&f.isFile()) {
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			OutputStream os = null;
			BufferedOutputStream bos = null;
			try {
				fis = new FileInputStream(f);// data source(서버측 파일)
				bis = new BufferedInputStream(fis);
				os = response.getOutputStream();// data Destination(클라이언트 브라우저)
				bos = new BufferedOutputStream(os);
				
				int count = 0;
				byte b[] = new byte[1024];
				while((count = bis.read(b)) !=-1) {
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
				}catch(IOException ie) {	
				}
			}
		}
	}
}
		