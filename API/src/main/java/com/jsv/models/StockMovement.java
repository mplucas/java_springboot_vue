package com.jsv.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.jsv.models.compositeKeys.StockMovementID;

@Entity
@IdClass(StockMovementID.class)
@Table(name = "StockMovements")
public class StockMovement implements Serializable {

	public enum StockMovementType {
		Entrada, Saída
	}

	@Id
	@Column(name = "ProductID", length = 250, nullable = false)
	private String productID;
	@Id
	@Column(name = "SellDate", columnDefinition = "DATETIME", nullable = false)
	private LocalDateTime sellDate;
	@Column(name = "Type", length = 250, nullable = false)
	private StockMovementType type;
	@Column(name = "SellPrice")
	private double sellPrice;
	@Column(name = "QuantityMoved", nullable = false)
	private double quantityMoved;

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
