package model;

public class SellPeriod {
	private int spid;
	private int se_productNumber;
	private String se_sellPeriod;
	private String se_productName;
	private String se_productColor;
	private int se_productStock;
	private int se_productSize;	
	private int se_sellPrice;
	private int se_cid;
	private String se_customerName;
	private String se_gender;
	private String se_birthday;
	private String se_phoneNumber;
	private int se_userPoint;
	
	public SellPeriod(int se_productNumber, String se_sellPeriod, String se_productName, String se_productColor,
			int se_productStock, int se_productSize, int se_sellPrice, int se_cid, String se_customerName,
			String se_gender, String se_birthday, String se_phoneNumber, int se_userPoint) {
		super();
		this.se_productNumber = se_productNumber;
		this.se_sellPeriod = se_sellPeriod;
		this.se_productName = se_productName;
		this.se_productColor = se_productColor;
		this.se_productStock = se_productStock;
		this.se_productSize = se_productSize;
		this.se_sellPrice = se_sellPrice;
		this.se_cid = se_cid;
		this.se_customerName = se_customerName;
		this.se_gender = se_gender;
		this.se_birthday = se_birthday;
		this.se_phoneNumber = se_phoneNumber;
		this.se_userPoint = se_userPoint;
	}
	public SellPeriod(int spid, int se_productNumber, String se_sellPeriod, String se_productName,
			String se_productColor, int se_productStock, int se_productSize, int se_sellPrice, int se_cid,
			String se_customerName, String se_gender, String se_birthday, String se_phoneNumber, int se_userPoint) {
		super();
		this.spid = spid;
		this.se_productNumber = se_productNumber;
		this.se_sellPeriod = se_sellPeriod;
		this.se_productName = se_productName;
		this.se_productColor = se_productColor;
		this.se_productStock = se_productStock;
		this.se_productSize = se_productSize;
		this.se_sellPrice = se_sellPrice;
		this.se_cid = se_cid;
		this.se_customerName = se_customerName;
		this.se_gender = se_gender;
		this.se_birthday = se_birthday;
		this.se_phoneNumber = se_phoneNumber;
		this.se_userPoint = se_userPoint;
	}
	public int getSpid() {
		return spid;
	}
	public void setSpid(int spid) {
		this.spid = spid;
	}
	public int getSe_productNumber() {
		return se_productNumber;
	}
	public void setSe_productNumber(int se_productNumber) {
		this.se_productNumber = se_productNumber;
	}
	public String getSe_sellPeriod() {
		return se_sellPeriod;
	}
	public void setSe_sellPeriod(String se_sellPeriod) {
		this.se_sellPeriod = se_sellPeriod;
	}
	public String getSe_productName() {
		return se_productName;
	}
	public void setSe_productName(String se_productName) {
		this.se_productName = se_productName;
	}
	public String getSe_productColor() {
		return se_productColor;
	}
	public void setSe_productColor(String se_productColor) {
		this.se_productColor = se_productColor;
	}
	public int getSe_productStock() {
		return se_productStock;
	}
	public void setSe_productStock(int se_productStock) {
		this.se_productStock = se_productStock;
	}
	public int getSe_productSize() {
		return se_productSize;
	}
	public void setSe_productSize(int se_productSize) {
		this.se_productSize = se_productSize;
	}
	public int getSe_sellPrice() {
		return se_sellPrice;
	}
	public void setSe_sellPrice(int se_sellPrice) {
		this.se_sellPrice = se_sellPrice;
	}
	public int getSe_cid() {
		return se_cid;
	}
	public void setSe_cid(int se_cid) {
		this.se_cid = se_cid;
	}
	public String getSe_customerName() {
		return se_customerName;
	}
	public void setSe_customerName(String se_customerName) {
		this.se_customerName = se_customerName;
	}
	public String getSe_gender() {
		return se_gender;
	}
	public void setSe_gender(String se_gender) {
		this.se_gender = se_gender;
	}
	public String getSe_birthday() {
		return se_birthday;
	}
	public void setSe_birthday(String se_birthday) {
		this.se_birthday = se_birthday;
	}
	public String getSe_phoneNumber() {
		return se_phoneNumber;
	}
	public void setSe_phoneNumber(String se_phoneNumber) {
		this.se_phoneNumber = se_phoneNumber;
	}
	public int getSe_userPoint() {
		return se_userPoint;
	}
	public void setSe_userPoint(int se_userPoint) {
		this.se_userPoint = se_userPoint;
	}
}
