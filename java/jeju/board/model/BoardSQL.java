package jeju.board.model;

class BoardSQL {
	static final String LIST_ALL = "select * from (select rownum rnum , aa.* from (select * from BOARD order by B_NUM desc) aa ) where rnum > ? and rnum <= ?";
	static final String BOARD_SIZE = "select * from BOARD";
	static final String CONTENT = "select * from BOARD where B_NUM=?";
	static final String INSERT = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, ?, 0, 0, 0, ?, ?, SYSDATE, SYSDATE, ?)";
	static final String DELETE = "delete from BOARD where B_NUM=?";
	static final String UPDATE = "update BOARD set SUBJECT=?, KATEGORIE=?, TAG=?, CONTENT=?, B_PHOTO=?, B_ORI_PHOTO=?, UP_DATE=SYSDATE, DIVISION=? where B_NUM=?";
	static final String VIEWS_CHECK = "select VIEWS from BOARD where B_NUM=?";
	static final String VIEWS_UP = "update BOARD set VIEWS=? where B_NUM=?";
	static final String LOVE_CHECK = "select LOVE from BOARD where B_NUM=?";
	static final String LOVE_UP = "update BOARD set LOVE=? where B_NUM=?";
	static final String HATE_CHECK = "select HATE from BOARD where B_NUM=?";
	static final String HATE_UP = "update BOARD set HATE=? where B_NUM=?";
}
