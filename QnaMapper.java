package soo.md.mapper;

import java.util.List;

import soo.md.domain.Qna;
import soo.md.domain.QnaVo;

public interface QnaMapper {
	List<Qna> selectPerPage(QnaVo qnaVo);
	Qna selectBySeq(long seq);
	long selectCount();
	Object test(String surf);
	Integer searchSelectSubjectCount(String surf);
	Integer searchSelectContentCount(String surf);
	
	List<Qna> list();
	void insert(Qna qna);
	void update(Qna qna);
	void reply(Qna qna);
	void delete(long seq);
	void hits(long seq);
	int getGroupOrd(long seq);
	List<Qna> selectSearchSubject(QnaVo qnaVo);
	List<Qna> selectSearchContent(QnaVo qnaVo);
	List<Qna> selectBySubject(String surf);
	List<Qna> selectByContent(String surf);
}
