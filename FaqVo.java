package soo.md.domain;//Vo는 페이징 때문에 만든 파일이다.

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FaqVo {
	private int cp = 1; //페이지 번호
	private int ps = 5; //페이지 사이즈
	private String blank; 
	
	public int getStartRow() {
		return (cp-1)*ps; //
	}
	public int getEndRow() {
		return cp*ps; //
	}
	public String getBlank() {
		return blank;
	}
	
}
