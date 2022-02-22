package soo.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import soo.md.domain.Board;
import soo.md.domain.BoardListResult;
import soo.md.domain.BoardVo;
import soo.md.mapper.BoardMapper;


@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public BoardListResult getBoardListResult(int cp, int ps) {
		long totalCount = boardMapper.selectCount();
		BoardVo boardVo = new BoardVo(cp, ps);
		List<Board> list = boardMapper.selectPerPage(boardVo);
		
		return new BoardListResult(cp, totalCount, ps, list);		
	}
	@Override
	public Board getBoard(long seq) {
		Board board = boardMapper.selectBySeq(seq);
		return board;
	}
	@Override
	public void write(Board board) {
		boardMapper.insert(board);	
	}
	@Override
	public void edit(Board board) {
		boardMapper.update(board);	
	}
	@Override
	public void remove(long seq) {
		boardMapper.delete(seq);	
	}
}
