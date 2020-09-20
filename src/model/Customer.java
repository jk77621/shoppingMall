package model;

public class Customer {
	private int cid;
	private String ct_customerName;
	private String ct_gender;
	private String ct_birthday;
	private String ct_phoneNumber;
	private int ct_userPoint;
	
	public Customer(int cid, String ct_customerName, String ct_gender, String ct_birthday, String ct_phoneNumber,
			int ct_userPoint) {
		super();
		this.cid = cid;
		this.ct_customerName = ct_customerName;
		this.ct_gender = ct_gender;
		this.ct_birthday = ct_birthday;
		this.ct_phoneNumber = ct_phoneNumber;
		this.ct_userPoint = ct_userPoint;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCt_customerName() {
		return ct_customerName;
	}

	public void setCt_customerName(String ct_customerName) {
		this.ct_customerName = ct_customerName;
	}

	public String getCt_gender() {
		return ct_gender;
	}

	public void setCt_gender(String ct_gender) {
		this.ct_gender = ct_gender;
	}

	public String getCt_birthday() {
		return ct_birthday;
	}

	public void setCt_birthday(String ct_birthday) {
		this.ct_birthday = ct_birthday;
	}

	public String getCt_phoneNumber() {
		return ct_phoneNumber;
	}

	public void setCt_phoneNumber(String ct_phoneNumber) {
		this.ct_phoneNumber = ct_phoneNumber;
	}

	public int getCt_userPoint() {
		return ct_userPoint;
	}

	public void setCt_userPoint(int ct_userPoint) {
		this.ct_userPoint = ct_userPoint;
	}
}
