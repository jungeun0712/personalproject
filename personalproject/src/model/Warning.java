package model;

public class Warning {
	private int num;
	private int seq;
	private String id;
	private String reason;
	private String warnid;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getWarnid() {
		return warnid;
	}
	public void setWarnid(String warnid) {
		this.warnid = warnid;
	}
	@Override
	public String toString() {
		return "Warning [num=" + num + ", seq=" + seq + ", id=" + id + ", reason=" + reason + ", warnid=" + warnid
				+ "]";
	}
}
