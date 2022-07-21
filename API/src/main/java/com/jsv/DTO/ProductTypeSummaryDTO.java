package com.jsv.DTO;

import com.jsv.enums.ProductTypeEnum;

public class ProductTypeSummaryDTO {
    private ProductTypeEnum productType;
    private double sellQuantity;
    private double currentQuantity;

    public ProductTypeSummaryDTO(ProductTypeEnum productType, double sellQuantity, double currentQuantity) {
        this.productType = productType;
        this.sellQuantity = sellQuantity;
        this.currentQuantity = currentQuantity;
    }

    public ProductTypeEnum getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeEnum productType) {
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
