package soo.ct.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import soo.ct.domain.Human;
import soo.ct.domain.HumanList;
import soo.ct.domain.ToDoDTO;

@Log4j
@Controller
@RequestMapping("/test")
//@RequestMapping("/test/*")
public class TestController {
	@RequestMapping("")
	public void m01() {
		log.info("#m01() - default URL");
	}
	@RequestMapping("/base1")
	public void m02() {
		log.info("#m02() - Get방식 & Post방식 & ...");
	}
	@RequestMapping(value="/base2", method = RequestMethod.GET)
	public void m03() {
		log.info("#m03() - Get방식");
	}
	@RequestMapping(value="/base3", method = {RequestMethod.GET, RequestMethod.POST})
	public void m04() {
		log.info("#m04() - Get방식 & Post방식");
	}
	@RequestMapping(value="/form", method = RequestMethod.GET)
	public String form() {
		return "test/form";
	}
	
	/*@RequestMapping(value="/param1", method = RequestMethod.GET)
	public void m05(String name) {
		System.out.println("#m05() - name: " + name);
	}*/
	@RequestMapping(value="/param1", method = RequestMethod.GET)
	public void m05(@RequestParam("na") String name, int age) {
		log.info("#m05() - name: " + name + ", age: " + age);
	}
	@RequestMapping(value="/param2")
	public void m06(Human dto) {
		log.info("#m06() - dto:" + dto);
	}
	@RequestMapping(value="/param3")
	public void m07(@RequestParam ArrayList<String> names) {
		log.info("#m07() - names:" + names);
	}
	@RequestMapping(value="/param4")
	public void m08(@RequestParam("ns") ArrayList<String> names) {
		log.info("#m08() - names:" + names);
	}
	@RequestMapping(value="/param5")
	public void m09(@RequestParam String[] names) {
		log.info("#m09() - names:" + names);
		for(String name: names) log.info("#name: " + name);
	}
	
	@RequestMapping(value="/param6")
	public void m10(HumanList hlist) {
		log.info("#m10() - hlist:" + hlist);
	}
	@RequestMapping(value="/param7")
	public void m11(String dump, @RequestParam("s") int seq, Human dto) {
		log.info("#m11() - dto:" + dto + ", dump: " + dump + ", seq: " + seq);
	}
	//@RequestMapping(value="/param8",  method = RequestMethod.GET)
	@GetMapping("/param8")
	public void m12(ToDoDTO dto) {
		log.info("#m12() - dto:" + dto);
		Date cdate = dto.getCdate();
		log.info("#m12() - cdate:" + cdate);
		
		showDate(cdate);
	}
	private void showDate(Date cdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(cdate);
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH); //1월 -> 0
		int d1 = cal.get(Calendar.DATE);
		int d2 = cal.get(Calendar.DAY_OF_WEEK); 
		String day = "";
		switch(d2) {
		    case 1: day = "일"; break;
		    case 2: day = "월"; break;
		    case 3: day = "화"; break;
		    case 4: day = "수"; break;
		    case 5: day = "목"; break;
		    case 6: day = "금"; break;
		    case 7: day = "토"; break;
		}
		log.info("#m12() : " + y + "년 "+(m+1)+"월 "+d1+"일("+day+")"); 
	}
}
