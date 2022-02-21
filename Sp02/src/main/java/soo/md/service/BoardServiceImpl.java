package soo.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soo.md.domain.Board;
import soo.md.mapper.BoardMapper;


@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<Board> listS() {
		return boardMapper.list();
	}
	@Override
	public void insertS(Board board) {
		boardMapper.insert(board);
	}
	@Override
	public Board content_listS(long seq) {
		return boardMapper.content_list(seq);
	}
	@Override
	public Board update_listS(long seq) {
		return boardMapper.update_list(seq);
	}
	@Override
	public void update_updateS(Board board) {
		boardMapper.update_update(board);
	}
	@Override
	public void deleteS(long seq) {
		boardMapper.delete(seq);
	}
}
