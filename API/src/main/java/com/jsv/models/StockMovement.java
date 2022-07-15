package com.jsv.models;

import java.util.Date;

public class StockMovement {
	private Product product;
	private String type;
	private double sellPrice;
	private Date sellDate;
	private double quantityMoved;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public double getQuantityMoved() {
		return quantityMoved;
	}
	public void setQuantityMoved(double quantityMoved) {
		this.quantityMoved = quantityMoved;
	}
}
