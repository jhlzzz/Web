package soo.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import soo.md.domain.Faq;
import soo.md.domain.FaqListResult;
import soo.md.domain.FaqVo;
import soo.md.mapper.FaqMapper;


@Service
@AllArgsConstructor
public class FaqServiceImpl implements FaqService{
	@Autowired
	private FaqMapper faqMapper;
	
	@Override
	public FaqListResult getFaqListResult(int cp, int ps) {
		long totalCount = faqMapper.selectCount();
		FaqVo faqVo = new FaqVo(cp, ps,"");
		List<Faq> list = faqMapper.selectPerPage(faqVo);
		
		return new FaqListResult(cp, totalCount, ps, list);		
	}
	@Override
	public Faq getFaq(long seq) {
		Faq faq = faqMapper.selectBySeq(seq);
		return faq;
	}
	@Override
	public void write(Faq faq) {
		faqMapper.insert(faq);	
	}
	@Override
	public void edit(Faq faq) {
		faqMapper.update(faq);	
	}
	@Override
	public void remove(long seq) {
		faqMapper.delete(seq);	
	}
	@Override
	public List<Faq> selectBySubject(String subject) {
		return faqMapper.selectBySubject(subject);
	}
	@Override
	public FaqListResult getFaqSearchListResult(int cp, int ps, String subject) {
		long totalCount = faqMapper.selectCount();
		FaqVo faqVo = new FaqVo(cp, ps,subject);
		List<Faq> list = faqMapper.selectSearchPerPage(faqVo);
		
		return new FaqListResult(cp, totalCount, ps, list);		
	}
}
