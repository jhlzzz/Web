package land.mvc.model;

class LandSQL {
	final static String LIST = "select * from LANDMARK";
	final static String LIST_ALL = "select * from (select rownum rnum , aa.* from (select * from LANDMARK order by lnum desc) aa ) where rnum > ? and rnum <= ?";
	final static String DELETE = "delete from LANDMARK where lnum=?";
	final static String CONTENT = "select * from LANDMARK where lnum=?";
	static final String BOARD_SIZE = "select * from LANDMARK";
}
