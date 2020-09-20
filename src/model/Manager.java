package model;

public class Manager {
	private int mid;
	private String mt_managerName;
	private String mt_managerId;
	private String mt_managerPassword;
	
	public Manager(String mt_managerName, String mt_managerId, String mt_managerPassword) {
		super();
		this.mt_managerName = mt_managerName;
		this.mt_managerId = mt_managerId;
		this.mt_managerPassword = mt_managerPassword;
	}

	public Manager(int mid, String mt_managerName, String mt_managerId, String mt_managerPassword) {
		super();
		this.mid = mid;
		this.mt_managerName = mt_managerName;
		this.mt_managerId = mt_managerId;
		this.mt_managerPassword = mt_managerPassword;
	}
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMt_managerName() {
		return mt_managerName;
	}
	public void setMt_managerName(String mt_managerName) {
		this.mt_managerName = mt_managerName;
	}
	public String getMt_managerId() {
		return mt_managerId;
	}
	public void setMt_managerId(String mt_managerId) {
		this.mt_managerId = mt_managerId;
	}
	public String getMt_managerPassword() {
		return mt_managerPassword;
	}
	public void setMt_managerPassword(String mt_managerPassword) {
		this.mt_managerPassword = mt_managerPassword;
	}
	
}
