package com.jsv.DTO;

import com.jsv.models.Product.ProductType;

public class ProductTypeSummaryDTO {
    private ProductType productType;
    private double sellQuantity;
    private double currentQuantity;

    public ProductTypeSummaryDTO(ProductType productType, double sellQuantity, double currentQuantity) {
        this.productType = productType;
        this.sellQuantity = sellQuantity;
        this.currentQuantity = currentQuantity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public double getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(double currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
}
