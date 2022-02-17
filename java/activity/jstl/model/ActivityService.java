package activity.jstl.model;

import java.util.ArrayList;

import mvc.domain.Activity;
import mvc.domain.Course;

public class ActivityService {
	private ActivityRepository rep;
	
	private static final ActivityService instance = new ActivityService();
	private ActivityService() {
		rep = new ActivityRepository();
	}
	public static ActivityService getInstance() {
		return instance;
	}
	public ArrayList<Activity> listS(){
		return rep.list();
	}
	public ArrayList<Activity> list_conS(int seq) {
		return rep.list_con(seq);
	}
}
