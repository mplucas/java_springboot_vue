package com.jsv.DTO;

public class ProductBalanceSummaryDTO {
    private String productID;
    private double sellQuantity;
    private double totalProfit;

    public ProductBalanceSummaryDTO(String productID, double sellQuantity, double totalProfit) {
        this.productID = productID;
        this.sellQuantity = sellQuantity;
        this.totalProfit = totalProfit;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }
}
