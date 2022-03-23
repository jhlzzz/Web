package soo.md.service;

import java.util.List;

import soo.md.domain.Faq;
import soo.md.domain.FaqListResult;
import soo.md.domain.FaqSearchListResult;

public interface FaqService {
	FaqListResult getFaqListResult(int cp, int ps);
	Faq getFaq(long seq);
	
	void write(Faq faq);
	void edit(Faq faq);
	void remove(long seq);
	List<Faq> selectBySubject(String subject);
	FaqSearchListResult getFaqSearchListResult(int cp, int ps, String blank, String search_key);
	List<Faq> selectByContent(String subject);
}
