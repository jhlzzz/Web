package course.jstl.model;

import java.util.ArrayList;

import mvc.domain.Course;

public class CourseService {
	private CourseRepository rep;
	
	private static final CourseService instance = new CourseService();
	private CourseService() {
		rep = new CourseRepository();
	}
	public static CourseService getInstance() {
		return instance;
	}
	public ArrayList<Course> listS(String division){
		return rep.list(division);
	}
	public Course list_conS(long seq) {
		return rep.list_con(seq);
	}
}
