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
	private long hits;
	private String surf;
	//관리자가 답글을 달때 원글을 작성한 사용자의 이메일
	private String writerEmail;
	
    /** 원글 번호 **/
    private int originNo;
    
    /** 원글(답글포함)에 대한 순서 **/
    private int groupOrd;
 
}
