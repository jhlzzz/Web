package soo.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import soo.md.domain.Faq;
import soo.md.domain.Qna;
import soo.md.domain.QnaListResult;
import soo.md.domain.QnaSearchListResult;
import soo.md.domain.QnaVo;
import soo.md.mapper.QnaMapper;


@Service
@AllArgsConstructor//this.변수 들을 생략해주는 @ ;ServiceImpl애서 넘어온 값으로 변수가 갱신됨
public class QnaServiceImpl implements QnaService {
	@Autowired
	private QnaMapper qnaMapper;
	
	@Override
	public QnaListResult getQnaListResult(int cp, int ps) {
		long totalCount = qnaMapper.selectCount();
		QnaVo qnaVo = new QnaVo(cp, ps,"");
		List<Qna> list = qnaMapper.selectPerPage(qnaVo);
		return new QnaListResult(cp, totalCount, ps, list);		
	}
	@Override
	public Qna getQna(long seq) {
		qnaMapper.hits(seq);
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
	@Override
	public List<Qna> selectBySubject(String surf) {
		return qnaMapper.selectBySubject(surf);
	}
	@Override
	public List<Qna> selectByContent(String surf) {
		return qnaMapper.selectByContent(surf);
	}
	@Override
	public QnaSearchListResult getQnaSearchListResult(int cp, int ps, String surf, String search_key) {
		Integer totalCount;
		QnaVo qnaVo = new QnaVo(cp, ps,surf);
		List<Qna> list;
		if("subject".equals(search_key)) {
			totalCount = qnaMapper.searchSelectSubjectCount(surf);
			list = qnaMapper.selectSearchSubject(qnaVo);
		}else {
			totalCount = qnaMapper.searchSelectContentCount(surf);

			list = qnaMapper.selectSearchContent(qnaVo);
		}
		if(totalCount == null) {
			totalCount = 0;
		}
		return new QnaSearchListResult(cp, totalCount, ps, list, surf, search_key);		
	}
	
}
