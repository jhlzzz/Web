package soo.md.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import soo.md.domain.Board;
import soo.md.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list.do")
	public ModelAndView list() {
		List<Board> list = boardService.listS();
		ModelAndView mv = new ModelAndView("board/list", "list", list);
		return mv;
	}
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	@PostMapping("/write.do")
	public String write(Board board) {
		boardService.insertS(board);
		return "redirect:list.do";
	}
	@GetMapping("/content_list.do")
	public ModelAndView content_list(long seq) {
		Board content_list = boardService.content_listS(seq);
		ModelAndView mv = new ModelAndView("board/content_list", "content_list", content_list);
		return mv;
	}
	@GetMapping("/update_list.do")
	public ModelAndView update_list(long seq) {
		Board update_list = boardService.update_listS(seq);
		ModelAndView mv = new ModelAndView("board/update_list", "update_list", update_list);
		return mv;
	}
	@PostMapping("/update_update.do")
	public String update_update(Board board) {
		boardService.update_updateS(board);
		return "redirect:list.do";
	}
	@GetMapping("/del.do")
	public String delete(long seq) {
		boardService.deleteS(seq);
		return "redirect:list.do";
	}
	
}
