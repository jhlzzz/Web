package board.mvc.model;

class BoardSQL {
	static final String LIST= "select * from board order by seq desc";
	static final String INSERT= "insert into board values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	static final String LIST_CON= "select * from board where seq=";
	static final String UPDATE_LIST= "select * from board where seq=?";
	static final String UPDATE_UPDATE= "update board set email=?, subject=?, content=? where seq=?";
	static final String DELETE= "delete from board where seq=?";
}
