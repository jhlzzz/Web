package soo.md.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FaqSearchListResult {
	private List<Faq> list;
	private int cp;
	private int ps;
	private long totalCount;
	private long totalPageCount;
	private String blank;
	private String search_key;
	
	public FaqSearchListResult(int cp, long totalCount, int ps, List<Faq> list, String blank, String search_key) {
		this.cp = cp;
		this.totalCount = totalCount;
		this.ps = ps;
		this.list = list;
		this.blank = blank;
		this.search_key = search_key;
		this.totalPageCount = calTotalPageCount();
	}
	private long calTotalPageCount() {
		long tpc = totalCount/ps;
		if(totalCount%ps != 0) tpc++;
		
		return tpc;
	}
}
