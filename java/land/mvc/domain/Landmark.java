package land.mvc.domain;

import java.sql.Date;

public class Landmark {
	private int lnum;
	private String lname;
	private String lintro;
	private int lcost;
	private String laddress;
	private String lphone;
	private Date lopcl;
	private Date lclosed;
	private String lphoto;
	private String loriphoto;
	private int choosed;
	private String review;
	private int star;
	private int views;
	private int division;
	
	public Landmark() {
		super();
	}
	
	public Landmark(int lnum, String lname, String lintro, int lcost, String laddress, String lphone, Date lopcl,
			Date lclosed, String lphoto, String loriphoto, int choosed, String review, int star, int views,
			int division) {
		super();
		this.lnum = lnum;
		this.lname = lname;
		this.lintro = lintro;
		this.lcost = lcost;
		this.laddress = laddress;
		this.lphone = lphone;
		this.lopcl = lopcl;
		this.lclosed = lclosed;
		this.lphoto = lphoto;
		this.loriphoto = loriphoto;
		this.choosed = choosed;
		this.review = review;
		this.star = star;
		this.views = views;
		this.division = division;
	}
	
	public int getLnum() {
		return lnum;
	}

	public void setLnum(int lnum) {
		this.lnum = lnum;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getLintro() {
		return lintro;
	}

	public void setLintro(String lintro) {
		this.lintro = lintro;
	}

	public int getLcost() {
		return lcost;
	}

	public void setLcost(int lcost) {
		this.lcost = lcost;
	}

	public String getLaddress() {
		return laddress;
	}

	public void setLaddress(String laddress) {
		this.laddress = laddress;
	}

	public String getLphone() {
		return lphone;
	}

	public void setLphone(String lphone) {
		this.lphone = lphone;
	}

	public Date getLopcl() {
		return lopcl;
	}

	public void setLopcl(Date lopcl) {
		this.lopcl = lopcl;
	}

	public Date getLclosed() {
		return lclosed;
	}

	public void setLclosed(Date lclosed) {
		this.lclosed = lclosed;
	}

	public String getLphoto() {
		return lphoto;
	}

	public void setLphoto(String lphoto) {
		this.lphoto = lphoto;
	}

	public String getLoriphoto() {
		return loriphoto;
	}

	public void setLoriphoto(String loriphoto) {
		this.loriphoto = loriphoto;
	}

	public int getChoosed() {
		return choosed;
	}

	public void setChoosed(int choosed) {
		this.choosed = choosed;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getDivision() {
		return division;
	}

	public void setDivision(int division) {
		this.division = division;
	}
}
