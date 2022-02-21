package soo.md.service;

import java.util.List;

import soo.md.domain.Board;

public interface BoardService {
	List<Board> listS();
	void insertS(Board board);
	Board content_listS(long seq);
	Board update_listS(long seq);
	void update_updateS(Board board);
	void deleteS(long seq);
}
