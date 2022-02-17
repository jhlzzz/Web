package activity.mvc.domain;

public class Activity {
	private int anum;
	private String aname;
	private String aintro;
	private int acost;
	private String aaddress;
	private String aphone;
	private String aopcl;
	private String aclosed;
	private String aphoto;
	private String aoriphoto;
	private int choosed;
	private String review;
	private int star;
	private int views;
	private int division;
	
	public Activity() {
		super();
	}
	
	public Activity(int anum, String aname, String aintro, int acost, String aaddress, String aphone, String aopcl,
			String aclosed, String aphoto, String aoriphoto, int choosed, String review, int star, int views,
			int division) {
		super();
		this.anum = anum;
		this.aname = aname;
		this.aintro = aintro;
		this.acost = acost;
		this.aaddress = aaddress;
		this.aphone = aphone;
		this.aopcl = aopcl;
		this.aclosed = aclosed;
		this.aphoto = aphoto;
		this.aoriphoto = aoriphoto;
		this.choosed = choosed;
		this.review = review;
		this.star = star;
		this.views = views;
		this.division = division;
	}

	public int getAnum() {
		return anum;
	}

	public void setAnum(int anum) {
		this.anum = anum;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAintro() {
		return aintro;
	}

	public void setAintro(String aintro) {
		this.aintro = aintro;
	}

	public int getAcost() {
		return acost;
	}

	public void setAcost(int acost) {
		this.acost = acost;
	}

	public String getAaddress() {
		return aaddress;
	}

	public void setAaddress(String aaddress) {
		this.aaddress = aaddress;
	}

	public String getAphone() {
		return aphone;
	}

	public void setAphone(String aphone) {
		this.aphone = aphone;
	}

	public String getAopcl() {
		return aopcl;
	}

	public void setAopcl(String aopcl) {
		this.aopcl = aopcl;
	}

	public String getAclosed() {
		return aclosed;
	}

	public void setAclosed(String aclosed) {
		this.aclosed = aclosed;
	}

	public String getAphoto() {
		return aphoto;
	}

	public void setAphoto(String aphoto) {
		this.aphoto = aphoto;
	}

	public String getAoriphoto() {
		return aoriphoto;
	}

	public void setAoriphoto(String aoriphoto) {
		this.aoriphoto = aoriphoto;
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
