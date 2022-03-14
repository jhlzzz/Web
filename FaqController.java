package soo.md.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import soo.md.domain.Faq;
import soo.md.domain.FaqListResult;
import soo.md.service.FaqService;

@AllArgsConstructor
@Controller
@RequestMapping("/faq")
public class FaqController {
	@Autowired
	private FaqService faqService;
	
	@GetMapping("/list.do")
	public ModelAndView list(HttpServletRequest request, HttpSession session) {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		int cp = 1;
 		if(cpStr==null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);	
		}
		session.setAttribute("cp", cp);
		
		int ps = 3;
 		if(psStr==null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps!=psParam) {
					cp = 1;
					session.setAttribute("cp", cp);					
				}
			}
			ps = psParam;
		}
		session.setAttribute("ps", ps);
		
		FaqListResult listResult = faqService.getFaqListResult(cp, ps);
		ModelAndView mv = new ModelAndView("faq/list", "listResult", listResult);
		
		if(listResult.getList().size() == 0) {
			if(cp > 1) 
				return new ModelAndView("redirect:list.do?cp="+(cp-1));
			else
				return new ModelAndView("faq/list", "listResult", null);
		}else {
			return mv;
		}
	}
	@GetMapping("/write.do")
	public String write() {
		return "faq/write";
	}
	@PostMapping("/write.do")
	public String write(Faq faq) {
		faqService.write(faq);
		return "redirect:list.do?cp=1";
	}
	@GetMapping("/content.do")
	public ModelAndView content(long seq) {
		Faq faq = faqService.getFaq(seq);
		ModelAndView mv = new ModelAndView("faq/content", "faq", faq);
		return mv;
	}
	@GetMapping("/update.do")
	public ModelAndView update(long seq) {
		Faq faq = faqService.getFaq(seq);
		ModelAndView mv = new ModelAndView("faq/update", "faq", faq);
		return mv;
	}
	@PostMapping("/update.do")
	public String update(Faq faq) {
		faqService.edit(faq);
		return "redirect:list.do";
	}
	@GetMapping("/del.do")
	public String delete(long seq) {
		faqService.remove(seq);
		return "redirect:list.do";
	}
	@PostMapping("autoData.json")
	public @ResponseBody List<Faq> getFaqList(String subject){
		List<Faq> list = faqService.selectBySubject(subject);
		return list;
	}
	@GetMapping("/search.do")
	public ModelAndView search(HttpServletRequest request, HttpSession session) {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		String subject = request.getParameter("subject");
		int cp = 1;
 		if(cpStr==null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);	
		}
		session.setAttribute("cp", cp);
		
		int ps = 3;
 		if(psStr==null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps!=psParam) {
					cp = 1;
					session.setAttribute("cp", cp);					
				}
			}
			ps = psParam;
		}
		session.setAttribute("ps", ps);
		
		FaqListResult listResult = faqService.getFaqSearchListResult(cp, ps,subject);
		ModelAndView mv = new ModelAndView("faq/searchList", "listResult", listResult);
		
		if(listResult.getList().size() == 0) {
			if(cp > 1) 
				return new ModelAndView("redirect:search.do?cp="+(cp-1));
			else
				return new ModelAndView("faq/searchList", "listResult", null);
		}else {
			return mv;
		}
	}
}