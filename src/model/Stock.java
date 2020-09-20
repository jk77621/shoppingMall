package model;

public class Stock {
	// 1. member field
	private int stid;
	private int st_productNumber;
	private String st_productName;
	private String st_productColor;
	private int st_productStock;
	private int st_productSize;
	private int st_customerPrice;
	private int st_supplyPrice;
	private int st_supplyPriceTotal;
	private int st_sellPrice;
	private int st_sellPriceTotal;
	private int st_stock85;
	private int st_stock90;
	private int st_stock95;
	private int st_stock100;
	private int st_stock105;
	private int st_stock110;
	private int st_stock115;
	private String st_image;
	private int st_salePercent;
	public Stock(int st_productNumber, String st_productName, String st_productColor, int st_productStock,
			int st_productSize, int st_customerPrice, int st_supplyPrice, int st_supplyPriceTotal, int st_sellPrice,
			int st_sellPriceTotal, int st_stock85, int st_stock90, int st_stock95, int st_stock100, int st_stock105,
			int st_stock110, int st_stock115, String st_image, int st_salePercent) {
		super();
		this.st_productNumber = st_productNumber;
		this.st_productName = st_productName;
		this.st_productColor = st_productColor;
		this.st_productStock = st_productStock;
		this.st_productSize = st_productSize;
		this.st_customerPrice = st_customerPrice;
		this.st_supplyPrice = st_supplyPrice;
		this.st_supplyPriceTotal = st_supplyPriceTotal;
		this.st_sellPrice = st_sellPrice;
		this.st_sellPriceTotal = st_sellPriceTotal;
		this.st_stock85 = st_stock85;
		this.st_stock90 = st_stock90;
		this.st_stock95 = st_stock95;
		this.st_stock100 = st_stock100;
		this.st_stock105 = st_stock105;
		this.st_stock110 = st_stock110;
		this.st_stock115 = st_stock115;
		this.st_image = st_image;
		this.st_salePercent = st_salePercent;
	}
	public Stock(int stid, int st_productNumber, String st_productName, String st_productColor, int st_productStock,
			int st_productSize, int st_customerPrice, int st_supplyPrice, int st_supplyPriceTotal, int st_sellPrice,
			int st_sellPriceTotal, int st_stock85, int st_stock90, int st_stock95, int st_stock100, int st_stock105,
			int st_stock110, int st_stock115, String st_image, int st_salePercent) {
		super();
		this.stid = stid;
		this.st_productNumber = st_productNumber;
		this.st_productName = st_productName;
		this.st_productColor = st_productColor;
		this.st_productStock = st_productStock;
		this.st_productSize = st_productSize;
		this.st_customerPrice = st_customerPrice;
		this.st_supplyPrice = st_supplyPrice;
		this.st_supplyPriceTotal = st_supplyPriceTotal;
		this.st_sellPrice = st_sellPrice;
		this.st_sellPriceTotal = st_sellPriceTotal;
		this.st_stock85 = st_stock85;
		this.st_stock90 = st_stock90;
		this.st_stock95 = st_stock95;
		this.st_stock100 = st_stock100;
		this.st_stock105 = st_stock105;
		this.st_stock110 = st_stock110;
		this.st_stock115 = st_stock115;
		this.st_image = st_image;
		this.st_salePercent = st_salePercent;
	}
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	public int getSt_productNumber() {
		return st_productNumber;
	}
	public void setSt_productNumber(int st_productNumber) {
		this.st_productNumber = st_productNumber;
	}
	public String getSt_productName() {
		return st_productName;
	}
	public void setSt_productName(String st_productName) {
		this.st_productName = st_productName;
	}
	public String getSt_productColor() {
		return st_productColor;
	}
	public void setSt_productColor(String st_productColor) {
		this.st_productColor = st_productColor;
	}
	public int getSt_productStock() {
		return st_productStock;
	}
	public void setSt_productStock(int st_productStock) {
		this.st_productStock = st_productStock;
	}
	public int getSt_productSize() {
		return st_productSize;
	}
	public void setSt_productSize(int st_productSize) {
		this.st_productSize = st_productSize;
	}
	public int getSt_customerPrice() {
		return st_customerPrice;
	}
	public void setSt_customerPrice(int st_customerPrice) {
		this.st_customerPrice = st_customerPrice;
	}
	public int getSt_supplyPrice() {
		return st_supplyPrice;
	}
	public void setSt_supplyPrice(int st_supplyPrice) {
		this.st_supplyPrice = st_supplyPrice;
	}
	public int getSt_supplyPriceTotal() {
		return st_supplyPriceTotal;
	}
	public void setSt_supplyPriceTotal(int st_supplyPriceTotal) {
		this.st_supplyPriceTotal = st_supplyPriceTotal;
	}
	public int getSt_sellPrice() {
		return st_sellPrice;
	}
	public void setSt_sellPrice(int st_sellPrice) {
		this.st_sellPrice = st_sellPrice;
	}
	public int getSt_sellPriceTotal() {
		return st_sellPriceTotal;
	}
	public void setSt_sellPriceTotal(int st_sellPriceTotal) {
		this.st_sellPriceTotal = st_sellPriceTotal;
	}
	public int getSt_stock85() {
		return st_stock85;
	}
	public void setSt_stock85(int st_stock85) {
		this.st_stock85 = st_stock85;
	}
	public int getSt_stock90() {
		return st_stock90;
	}
	public void setSt_stock90(int st_stock90) {
		this.st_stock90 = st_stock90;
	}
	public int getSt_stock95() {
		return st_stock95;
	}
	public void setSt_stock95(int st_stock95) {
		this.st_stock95 = st_stock95;
	}
	public int getSt_stock100() {
		return st_stock100;
	}
	public void setSt_stock100(int st_stock100) {
		this.st_stock100 = st_stock100;
	}
	public int getSt_stock105() {
		return st_stock105;
	}
	public void setSt_stock105(int st_stock105) {
		this.st_stock105 = st_stock105;
	}
	public int getSt_stock110() {
		return st_stock110;
	}
	public void setSt_stock110(int st_stock110) {
		this.st_stock110 = st_stock110;
	}
	public int getSt_stock115() {
		return st_stock115;
	}
	public void setSt_stock115(int st_stock115) {
		this.st_stock115 = st_stock115;
	}
	public String getSt_image() {
		return st_image;
	}
	public void setSt_image(String st_image) {
		this.st_image = st_image;
	}
	public int getSt_salePercent() {
		return st_salePercent;
	}
	public void setSt_salePercent(int st_salePercent) {
		this.st_salePercent = st_salePercent;
	}
	
	
}
