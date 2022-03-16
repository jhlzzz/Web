package soo.md.mapper;

import java.util.List;

import soo.md.domain.Qna;
import soo.md.domain.QnaVo;

public interface QnaMapper {
	List<Qna> selectPerPage(QnaVo qnaVo);
	Qna selectBySeq(long seq);
	long selectCount();
	
	List<Qna> list();
	void insert(Qna qna);
	void update(Qna qna);
	void reply(Qna qna);
	void delete(long seq);
	int getGroupOrd(long seq);
}
