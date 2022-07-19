package com.jsv.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;

@Entity
@Table(name = "StockMovements")
public class StockMovement {

	enum StockMovementType {
		Entrada, Saída
	}

	@Id
	@Column(name = "StockMovementID", length = 250, nullable = false)
	private String stockMovementID;
	@Column(name = "ProductID", length = 250, nullable = false)
	private String productID;
	@Column(name = "SellDate", columnDefinition = "DATETIME", nullable = false)
	private LocalDateTime sellDate;
	@Column(name = "Type", length = 250, nullable = false)
	private StockMovementType type;
	@Column(name = "SellPrice")
	private double sellPrice;
	@Column(name = "QuantityMoved")
	private double quantityMoved;

	public void refreshStockMovementID() {
		this.stockMovementID = this.productID + this.sellDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	public String getStockMovementID() {
		return stockMovementID;
	}

	public void setStockMovementID(String stockMovementID) {
		this.stockMovementID = stockMovementID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public StockMovementType getType() {
		return type;
	}

	public void setType(StockMovementType type) {
		this.type = type;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public LocalDateTime getSellDate() {
		return sellDate;
	}

	public void setSellDate(LocalDateTime sellDate) {
		this.sellDate = sellDate;
	}

	public double getQuantityMoved() {
		return quantityMoved;
	}

	public void setQuantityMoved(double quantityMoved) {
		this.quantityMoved = quantityMoved;
	}
}
