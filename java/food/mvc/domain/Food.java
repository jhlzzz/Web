package food.mvc.domain;

import java.sql.Date;

public class Food {

	private int fnum;
	private String fname;
	private String fintro;
	private int fcost;
	private String faddress;
	private String fphone;
	private Date fopcl;
	private Date fbreak;
	private Date fclosed;
	private String fphoto;
	private String foriphoto;
	private int choosed;
	private String review;
	private int star;
	private int views;
	private int division;
	
	public Food() {
		super();
	}
	
	public Food(int fnum, String fname, String fintro, int fcost, String faddress, String fphone, Date fopcl, Date fbreak,
			Date fclosed, String fphoto, String foriphoto, int choosed, String review, int star, int views,
			int division) {
		super();
		this.fnum = fnum;
		this.fname = fname;
		this.fintro = fintro;
		this.fcost = fcost;
		this.faddress = faddress;
		this.fphone = fphone;
		this.fopcl = fopcl;
		this.fbreak = fbreak;
		this.fclosed = fclosed;
		this.fphoto = fphoto;
		this.foriphoto = foriphoto;
		this.choosed = choosed;
		this.review = review;
		this.star = star;
		this.views = views;
		this.division = division;
	}
	
	public int getFnum() {
		return fnum;
	}

	public void setFnum(int fnum) {
		this.fnum = fnum;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFintro() {
		return fintro;
	}

	public void setFintro(String fintro) {
		this.fintro = fintro;
	}

	public int getFcost() {
		return fcost;
	}

	public void setFcost(int fcost) {
		this.fcost = fcost;
	}

	public String getFaddress() {
		return faddress;
	}

	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}

	public String getFphone() {
		return fphone;
	}

	public void setFphone(String fphone) {
		this.fphone = fphone;
	}

	public Date getFopcl() {
		return fopcl;
	}

	public void setFopcl(Date fopcl) {
		this.fopcl = fopcl;
	}

	public Date getFbreak() {
		return fbreak;
	}

	public void setFbreak(Date fbreak) {
		this.fbreak = fbreak;
	}

	public Date getFclosed() {
		return fclosed;
	}

	public void setFclosed(Date fclosed) {
		this.fclosed = fclosed;
	}

	public String getFphoto() {
		return fphoto;
	}

	public void setFphoto(String fphoto) {
		this.fphoto = fphoto;
	}

	public String getForiphoto() {
		return foriphoto;
	}

	public void setForiphoto(String foriphoto) {
		this.foriphoto = foriphoto;
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
