package com.jsv.DAO;

import java.util.List;

import com.jsv.models.StockMovement;
import com.jsv.models.Product.ProductType;

public interface StockMovementDAO {
    public List<StockMovement> findAllStockMovements();

    public void saveStockMovement(StockMovement stockMovement);

    public boolean existsStockMovement(StockMovement stockMovement);

    public List<StockMovement> getStockMovementsBy(ProductType productType);

    public List<StockMovement> getStockMovementsBy(String productID);
}
