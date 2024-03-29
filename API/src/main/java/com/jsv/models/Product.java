package com.jsv.models;

import javax.persistence.*;

import com.jsv.enums.ProductTypeEnum;

@Entity
@Table(name = "Products")
public class Product {

	@Id
	@Column(name = "ProductID", length = 250, nullable = false)
	private String productID;
	@Column(name = "Description", length = 250)
	private String description;
	@Column(name = "Type", length = 250, nullable = false)
	private ProductTypeEnum type;
	@Column(name = "BuyPrice")
	private double buyPrice;
	@Column(name = "StockQuantity")
	private double stockQuantity;

	public Product() {

	}

	public Product(String productID, String description, ProductTypeEnum type, double buyPrice, double stockQuantity) {
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

	public ProductTypeEnum getType() {
		return type;
	}

	public void setType(ProductTypeEnum type) {
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

	public void addStockQuantity(double quantity) {
		this.stockQuantity += quantity;
	}
}
