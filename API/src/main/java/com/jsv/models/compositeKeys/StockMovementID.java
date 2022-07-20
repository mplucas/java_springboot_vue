package com.jsv.models.compositeKeys;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class StockMovementID implements Serializable {
    private String productID;
    private LocalDateTime sellDate;

    public StockMovementID() {

    }

    public StockMovementID(String productID, LocalDateTime sellDate) {
        this.productID = productID;
        this.sellDate = sellDate;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public LocalDateTime getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDateTime sellDate) {
        this.sellDate = sellDate;
    }
}
