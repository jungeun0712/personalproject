package model;

import java.util.Date;

public class Icon {
	private String id;
	private Date date;
	private int icon;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "Icon [id=" + id + ", date=" + date + ", icon=" + icon + "]";
	}
	
}
