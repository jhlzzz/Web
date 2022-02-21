package soo.md.mapper;

import java.util.List;

import soo.md.domain.Board;

public interface BoardMapper {
	List<Board> list();
	void insert(Board board);
	Board content_list(long seq);
	Board update_list(long seq);
	void update_update(Board board);
	void delete(long seq);
}
