package com.jsv.DAO;

import java.util.List;

import com.jsv.enums.ProductTypeEnum;
import com.jsv.models.StockMovement;

public interface StockMovementDAO {
    public List<StockMovement> findAllStockMovements();

    public void saveStockMovement(StockMovement stockMovement);

    public boolean existsStockMovement(StockMovement stockMovement);

    public List<StockMovement> getStockMovementsBy(ProductTypeEnum productType);

    public List<StockMovement> getStockMovementsBy(String productID);

    public void deleteStockMovementsBy(String productID);
}
