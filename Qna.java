package soo.md.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//세터,게터 있다고 생각
@NoArgsConstructor
@AllArgsConstructor
public class Qna {//여기서 보드는 보드매퍼엑스엠엘의 파라미터이다.
	private long seq;
	private long rnum;
	private String writer;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
	
	/*
     * 계층형 게시판을 위한 추가 필드
     * originNo, groupOrd, groupLayer 
     */
    
    /** 원글 번호 **/
    private int originNo;
    
    /** 원글(답글포함)에 대한 순서 **/
    private int groupOrd;
 
    /** 답글 계층 **/
    private int groupLayer;
}
