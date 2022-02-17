package activity.mvc.model;

class ActivitySQL {
	static final String LIST= "select * from ACTIVITY";
	static final String LIST_ALL = "select * from (select rownum rnum , aa.* from (select * from ACTIVITY order by anum desc) aa ) where rnum > ? and rnum <= ?";
	final static String DELETE = "delete from ACTIVITY where anum=?";
	final static String CONTENT = "select * from ACTIVITY where anum=?";
	static final String BOARD_SIZE = "select * from ACTIVITY";
}