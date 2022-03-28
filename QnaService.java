package soo.md.service;


import java.util.List;

import soo.md.domain.Qna;
import soo.md.domain.QnaListResult;
import soo.md.domain.QnaSearchListResult;

public interface QnaService {
	QnaListResult getQnaListResult(int cp, int ps);
	Qna getQna(long seq);
	int getGroupOrd(long seq);
	
	void write(Qna qna);
	void edit(Qna qna);
	void reply(Qna qna);
	void remove(long seq);
	QnaSearchListResult getQnaSearchListResult(int cp, int ps, String surf, String search_key);
	List<Qna> selectBySubject(String surf);
	List<Qna> selectByContent(String surf);
}
