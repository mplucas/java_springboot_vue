package com.jsv.DAO;

import java.util.List;

import com.jsv.models.StockMovement;

public interface StockMovementDAO {
    public List<StockMovement> findAllStockMovements();

    public void saveStockMovement(StockMovement stockMovement);

    public boolean existsStockMovement(StockMovement stockMovement);
}
