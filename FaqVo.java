package soo.md.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FaqVo {
	private int cp = 1; //페이지 번호
	private int ps = 5; //페이지 사이즈
	private String subject; 
	
	public int getStartRow() {
		return (cp-1)*ps; //
	}
	public int getEndRow() {
		return cp*ps; //
	}
	public String getSubject() {
		return subject;
	}
	
}
