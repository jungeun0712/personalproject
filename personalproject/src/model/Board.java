package model;
import java.util.Date;
public class Board {
	private int num;
	private String id;
	private int type;
	private String subject;
	private Date regdate;
	private int keyword;
	private String content;
	private int readcnt;
	private int grp;
	private String file1;
	private String checkpass;
	
	Question qst;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public Question getQst() {
		return qst;
	}
	public void setQst(Question qst) {
		this.qst = qst;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getKeyword() {
		return keyword;
	}
	public void setKeyword(int keyword) {
		this.keyword = keyword;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public int getGrp() {
		return grp;
	}
	public void setGrp(int grp) {
		this.grp = grp;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public String getCheckpass() {
		return checkpass;
	}
	public void setCheckpass(String checkpass) {
		this.checkpass = checkpass;
	}
	@Override
	public String toString() {
		return "Board [num=" + num + ", id=" + id + ", type=" + type + ", subject=" + subject + ", regdate=" + regdate
				+ ", keyword=" + keyword + ", content=" + content + ", readcnt=" + readcnt + ", grp=" + grp + ", file1="
				+ file1 + ", checkpass=" + checkpass + "]";
	}
	
}
