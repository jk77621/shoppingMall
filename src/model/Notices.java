package model;

public class Notices {
	private int no;
	private String userID;
	private String date;
	private String title;
	private String contents;
	
	public Notices(String userID, String date, String title, String contents) {
		super();
		this.userID = userID;
		this.date = date;
		this.title = title;
		this.contents = contents;
	}
	
	public Notices(int no, String userID, String date, String title, String contents) {
		super();
		this.no = no;
		this.userID = userID;
		this.date = date;
		this.title = title;
		this.contents = contents;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
