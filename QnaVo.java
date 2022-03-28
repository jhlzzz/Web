package soo.md.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class QnaVo {
	private int cp = 1; //페이지 번호
	private int ps = 10; //페이지 사이즈
	private String surf; 

	public int getStartRow() {
		return (cp-1)*ps; //
	}
	public int getEndRow() {
		return cp*ps; //
	}
	public String getSurf() {
		return surf;
	}
}
