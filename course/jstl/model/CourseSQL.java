package course.jstl.model;

class CourseSQL {
	static final String LIST6= "select * from course where division = -1";
	static final String LIST1= "select * from course where division = 1";
	static final String LIST2= "select * from course where division = 2";
	static final String LIST3= "select * from course where division = 3";
	static final String LIST4= "select * from course where division = 4";
	static final String LIST5= "select * from course where division = 5";
	//static final String INSERT= "insert into course values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	static final String LIST_CON= "select * from course where cnum=?";
	//static final String UPDATE_LIST= "select * from course where seq=?";
	//static final String UPDATE_UPDATE= "update course set email=?, subject=?, content=? where seq=?";
	//static final String DELETE= "delete from course where seq=?";
	//static final String JOIN= "insert into course values(MEMBER_SEQ.nextval, ?, ?, ?, ?, SYSDATE, SYSDATE)";
	

}