package com.jsv.models;

public class Product {

	private String productID;
	private String description;
	private String type;
	private double buyPrice;
	private double stockQuantity;
	
	public Product(String productID, String description, String type, double buyPrice, double stockQuantity) {
		super();
		this.productID = productID;
		this.description = description;
		this.type = type;
		this.buyPrice = buyPrice;
		this.stockQuantity = stockQuantity;
	}
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public double getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(double stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}
