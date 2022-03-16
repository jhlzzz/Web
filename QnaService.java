package soo.md.service;


import soo.md.domain.Qna;
import soo.md.domain.QnaListResult;

public interface QnaService {
	QnaListResult getQnaListResult(int cp, int ps);
	Qna getQna(long seq);
	int getGroupOrd(long seq);
	
	void write(Qna qna);
	void edit(Qna qna);
	void reply(Qna qna);
	void remove(long seq);
	
}
