package soo.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import soo.md.domain.Qna;
import soo.md.domain.QnaListResult;
import soo.md.domain.QnaVo;
import soo.md.mapper.QnaMapper;


@Service
@AllArgsConstructor
public class QnaServiceImpl implements QnaService {
	@Autowired
	private QnaMapper qnaMapper;
	
	@Override
	public QnaListResult getQnaListResult(int cp, int ps) {
		long totalCount = qnaMapper.selectCount();
		QnaVo qnaVo = new QnaVo(cp, ps);
		List<Qna> list = qnaMapper.selectPerPage(qnaVo);
		
		return new QnaListResult(cp, totalCount, ps, list);		
	}
	@Override
	public Qna getQna(long seq) {
		Qna qna = qnaMapper.selectBySeq(seq);
		return qna;
	}
	@Override
	public int getGroupOrd(long seq) {
		int go = qnaMapper.getGroupOrd(seq);
		return go;
	}
	@Override
	public void write(Qna qna) {
		qnaMapper.insert(qna);	
	}
	@Override
	public void edit(Qna qna) {
		qnaMapper.update(qna);	
	}
	@Override
	public void reply(Qna qna) {
		qnaMapper.reply(qna);	
	}
	@Override
	public void remove(long seq) {
		qnaMapper.delete(seq);	
	}
	
	
}
