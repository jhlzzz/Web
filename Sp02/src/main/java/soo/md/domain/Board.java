package soo.md.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//세터,게터 있다고 생각
@NoArgsConstructor
@AllArgsConstructor
public class Board {//여기서 보드는 보드매퍼엑스엠엘의 파라미터이다.
	private long seq;
	private String writer;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
}
