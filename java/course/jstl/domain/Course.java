package course.jstl.domain;

public class Course {
	private long cnum;
	private String cname;
	private String cintro;
	private String cnavi;
	private long choosed;
	private long love;
	private long hate;
	private long mnum;
	private long anum;
	private long lnum;
	private long snum;
	private String cphoto;
	private String coriphoto;
	private int division;

	public Course() {
		
	}
	public Course(long cnum, String cname, String cintro, String cnavi, long choosed, long love, long hate, long mnum,
			long anum, long lnum, long snum, String cphoto, String coriphoto, int division) {
		super();
		this.cnum = cnum;
		this.cname = cname;
		this.cintro = cintro;
		this.cnavi = cnavi;
		this.choosed = choosed;
		this.love = love;
		this.hate = hate;
		this.mnum = mnum;
		this.anum = anum;
		this.lnum = lnum;
		this.snum = snum;
		this.cphoto = cphoto;
		this.coriphoto = coriphoto;
		this.division = division;
	}
	public long getCnum() {
		return cnum;
	}
	public void setCnum(long cnum) {
		this.cnum = cnum;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCintro() {
		return cintro;
	}
	public void setCintro(String cintro) {
		this.cintro = cintro;
	}
	public String getCnavi() {
		return cnavi;
	}
	public void setCnavi(String cnavi) {
		this.cnavi = cnavi;
	}
	public long getChoosed() {
		return choosed;
	}
	public void setChoosed(long choosed) {
		this.choosed = choosed;
	}
	public long getLove() {
		return love;
	}
	public void setLove(long love) {
		this.love = love;
	}
	public long getHate() {
		return hate;
	}
	public void setHate(long hate) {
		this.hate = hate;
	}
	public long getMnum() {
		return mnum;
	}
	public void setMnum(long mnum) {
		this.mnum = mnum;
	}
	public long getAnum() {
		return anum;
	}
	public void setAnum(long anum) {
		this.anum = anum;
	}
	public long getLnum() {
		return lnum;
	}
	public void setLnum(long lnum) {
		this.lnum = lnum;
	}
	public long getSnum() {
		return snum;
	}
	public void setSnum(long snum) {
		this.snum = snum;
	}
	public String getCphoto() {
		return cphoto;
	}
	public void setCphoto(String cphoto) {
		this.cphoto = cphoto;
	}
	public String getCoriphoto() {
		return coriphoto;
	}
	public void setCoriphoto(String coriphoto) {
		this.coriphoto = coriphoto;
	}
	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
	}
	
}