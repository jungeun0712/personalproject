package model;

public class Question {
	private int num;
	private String question;
	private String pw;
	private String today;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	@Override
	public String toString() {
		return "Question [num=" + num + ", question=" + question + ", pw=" + pw + ", today=" + today + "]";
	}
}
