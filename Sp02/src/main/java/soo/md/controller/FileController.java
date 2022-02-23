package soo.md.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import soo.md.fileset.Path;
import soo.md.service.FileUploadService;

@Log4j
@AllArgsConstructor
@Controller
@RequestMapping("/file")
public class FileController {
	private FileUploadService service;
	
	@GetMapping("form.do")
	public String formFu() {
		return "file/form";
	}
	@PostMapping("upload.do")
	public String upload(@RequestParam String name, @RequestParam MultipartFile file) {
		String ofname = file.getOriginalFilename();
		if(ofname != null) ofname = ofname.trim();
		if(ofname.length() != 0) {
			String url = service.saveStore(file);
		}
		return "redirect:list.do"; //저장소의 파일리스트를 보여주는 액션
	}
	@GetMapping("list.do")
	public ModelAndView fileList() {
		File fStore = new File(Path.FILE_STORE);
		if(!fStore.exists()) fStore.mkdirs();
		File files[] = fStore.listFiles();
		
		return new ModelAndView("file/list", "files", files);
	}
	@GetMapping("del.do")
	public String del(@RequestParam String fname) {
		File file = new File(Path.FILE_STORE, fname);
		if(file.exists()) file.delete();
		
		return "redirect:list.do";
	}
	@GetMapping("download.do")
	public ModelAndView download(String fname) {
		File file = new File(Path.FILE_STORE, fname);
		if(file.exists()) {
			return new ModelAndView("fileDownloadView", "downloadFile", file); 
			//V폴더에 없는데 찾아가게 하려면 servlet-context.xml에서 <beans:bean id="fileDownloadView" class="soo.md.fileset.FileDownloadView" />를 추가해줘야 한다.
		}else {
			return new ModelAndView("redirect:list.do");
		}
	}
	@GetMapping("form_mt.do")
	public String formFuMt() {
		return "file/form_mt";
	}
	@PostMapping("upload_mt.do")
	public String uploadMt(@RequestParam("files") ArrayList<MultipartFile> files) { //여러개의 name(밸류값)들이 날라오니까 파라미터를 컬렉션으로 받는다. 바디에서 꺼낼 때도 당연히 for문으로 꺼낸다.
		for(MultipartFile file :files) {
			String ofname = file.getOriginalFilename();
			if(ofname != null) ofname = ofname.trim();
			if(ofname.length() != 0) {
				String url = service.saveStore(file);
			}
		}
		return "redirect:list.do";
	}
}