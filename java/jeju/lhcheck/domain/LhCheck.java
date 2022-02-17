package jeju.lhcheck.domain;

public class LhCheck {
	private long lhcnum;
	private int classfi;
	private long contentnum;
	private String nick;
	private int love;
	private int hate;
	public LhCheck() {}
	public LhCheck(long lhcnum, int classfi, long contentnum, String nick, int love, int hate) {
		super();
		this.lhcnum = lhcnum;
		this.classfi = classfi;
		this.contentnum = contentnum;
		this.nick = nick;
		this.love = love;
		this.hate = hate;
	}
	public long getLhcnum() {
		return lhcnum;
	}
	public void setLhcnum(long lhcnum) {
		this.lhcnum = lhcnum;
	}
	public int getClassfi() {
		return classfi;
	}
	public void setClassfi(int classfi) {
		this.classfi = classfi;
	}
	public long getContentnum() {
		return contentnum;
	}
	public void setContentnum(long contentnum) {
		this.contentnum = contentnum;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love = love;
	}
	public int getHate() {
		return hate;
	}
	public void setHate(int hate) {
		this.hate = hate;
	}
	
}
