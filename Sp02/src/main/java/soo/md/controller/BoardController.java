package soo.md.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import soo.md.domain.Board;
import soo.md.domain.BoardListResult;
import soo.md.service.BoardService;

@AllArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
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
		
		BoardListResult listResult = boardService.getBoardListResult(cp, ps);
		ModelAndView mv = new ModelAndView("board/list", "listResult", listResult);
		
		if(listResult.getList().size() == 0) {
			if(cp > 1) 
				return new ModelAndView("redirect:list.do?cp="+(cp-1));
			else
				return new ModelAndView("board/llist", "listResult", null);
		}else {
			return mv;
		}
	}
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	@PostMapping("/write.do")
	public String write(Board board) {
		boardService.write(board);
		return "redirect:list.do?cp=1";
	}
	@GetMapping("/content.do")
	public ModelAndView content(long seq) {
		Board board = boardService.getBoard(seq);
		ModelAndView mv = new ModelAndView("board/content", "board", board);
		return mv;
	}
	@GetMapping("/update.do")
	public ModelAndView update(long seq) {
		Board board = boardService.getBoard(seq);
		ModelAndView mv = new ModelAndView("board/update", "board", board);
		return mv;
	}
	@PostMapping("/update.do")
	public String update(Board board) {
		boardService.edit(board);
		return "redirect:list.do";
	}
	@GetMapping("/del.do")
	public String delete(long seq) {
		boardService.remove(seq);
		return "redirect:list.do";
	}
}