package soo.md.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import soo.md.domain.Qna;
import soo.md.domain.QnaListResult;
import soo.md.service.QnaService;

@AllArgsConstructor
@Controller
@RequestMapping("/qna")
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
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
		
		QnaListResult listResult = qnaService.getQnaListResult(cp, ps);
		ModelAndView mv = new ModelAndView("qna/list", "listResult", listResult);
		
		if(listResult.getList().size() == 0) {
			if(cp > 1) 
				return new ModelAndView("redirect:list.do?cp="+(cp-1));
			else
				return new ModelAndView("qna/llist", "listResult", null);
		}else {
			return mv;
		}
	}
	@GetMapping("/write.do")
	public String write() {
		return "qna/write";
	}
	@PostMapping("/write.do")
	public String write(Qna qna) {
		qnaService.write(qna);
		return "redirect:list.do?cp=1";
	}
	@GetMapping("/content.do")
	public ModelAndView content(long seq) {
		Qna qna = qnaService.getQna(seq);
		ModelAndView mv = new ModelAndView("qna/content", "qna", qna);
		return mv;
	}
	@GetMapping("/update.do")
	public ModelAndView update(long seq) {
		Qna qna = qnaService.getQna(seq);
		ModelAndView mv = new ModelAndView("qna/update", "qna", qna);
		return mv;
	}
	@PostMapping("/update.do")
	public String update(Qna qna) {
		qnaService.edit(qna);
		return "redirect:list.do";
	}
	@GetMapping("/del.do")
	public String delete(long seq) {
		qnaService.remove(seq);
		return "redirect:list.do";
	}
	@GetMapping("reply.do")
	public ModelAndView reply(long seq) {
		Qna qna = qnaService.getQna(seq);
		ModelAndView mv = new ModelAndView("qna/reply", "qna", qna);
		return mv;
	}
	@PostMapping("reply.do")
	public String reply(Qna qna) {
		//System.out.println(qna);
		int go = qnaService.getGroupOrd(qna.getSeq());
		qna.setGroupOrd(go+1);
		qnaService.reply(qna);
		return "redirect:list.do";
	}
}