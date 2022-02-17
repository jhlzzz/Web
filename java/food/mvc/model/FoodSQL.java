package food.mvc.model;

class FoodSQL {
	final static String LIST = "select * from FOOD";
	static final String LIST_ALL = "select * from (select rownum rnum , aa.* from (select * from FOOD order by fnum desc) aa ) where rnum > ? and rnum <= ?";
	final static String DELETE = "delete from FOOD where fnum=?";
	final static String CONTENT = "select * from Food where fnum=?";
	static final String BOARD_SIZE = "select * from FOOD";
}
