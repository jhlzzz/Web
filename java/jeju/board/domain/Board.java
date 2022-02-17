package jeju.board.domain;

import java.sql.Date;

public class Board {
	private long bnum;
	private String nick;
	private String subject;
	private String kategorie;
	private String tag;
	private String content;
	private long love;
	private long hate;
	private long views;
	private String bphoto;
	private String boriphoto;
	private Date bdate;
	private Date update;
	private int division;
	public Board() {}
	public Board(long bnum, String nick, String subject, String kategorie, String tag, String content, long love, long hate, long views, String bphoto, String boriphoto, Date bdate, Date update, int division) {
		this.bnum = bnum;
		this.nick = nick;
		this.subject = subject;
		this.kategorie = kategorie;
		this.tag = tag;
		this.content = content;
		this.love = love;
		this.hate = hate;
		this.views = views;
		this.bphoto = bphoto;
		this.boriphoto = boriphoto;
		this.bdate = bdate;
		this.update = update;
		this.division = division;
	}
	public long getBnum() {
		return bnum;
	}
	public void setBnum(long bNumber) {
		this.bnum = bNumber;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getKategorie() {
		return kategorie;
	}
	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public String getBphoto() {
		return bphoto;
	}
	public void setBphoto(String bphoto) {
		this.bphoto = bphoto;
	}
	public String getBoriphoto() {
		return boriphoto;
	}
	public void setBoriphoto(String boriphoto) {
		this.boriphoto = boriphoto;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public Date getUpdate() {
		return update;
	}
	public void setUpdate(Date update) {
		this.update = update;
	}
	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
	}
	
}
