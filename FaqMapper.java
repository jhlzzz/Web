package soo.md.mapper;

import java.util.List;

import soo.md.domain.Faq;
import soo.md.domain.FaqVo;

public interface FaqMapper {
	List<Faq> selectPerPage(FaqVo faqVo);
	Faq selectBySeq(long seq);
	long selectCount();
	
	List<Faq> list();
	void insert(Faq faq);
	void update(Faq faq);
	void delete(long seq);
	List<Faq> selectBySubject(String subject);
	List<Faq> selectSearchPerPage(FaqVo faqVo);
}
