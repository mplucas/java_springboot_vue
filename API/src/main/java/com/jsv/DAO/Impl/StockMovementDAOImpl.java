package com.jsv.DAO.Impl;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.jsv.DAO.StockMovementDAO;
import com.jsv.models.StockMovement;
import com.jsv.repository.ProductRepository;
import com.jsv.repository.StockMovementRepository;

public class StockMovementDAOImpl implements StockMovementDAO {

    private final StockMovementRepository stockMovementRepository;

    public StockMovementDAOImpl(ProductRepository productRepository, StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
        new ProductDAOImpl(productRepository, stockMovementRepository);
    }

    @Override
    public List<StockMovement> findAllStockMovements() {
        return stockMovementRepository.findAll();
    }

    @Override
    public void saveStockMovement(StockMovement stockMovement) {
        stockMovementRepository.save(stockMovement);
    }

    @Override
    public boolean existsStockMovement(StockMovement stockMovement) {
        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("type", "sellPrice", "quantityMoved");
        StockMovement searchSM = new StockMovement();
        searchSM.setProductID(stockMovement.getProductID());
        searchSM.setSellDate(stockMovement.getSellDate());
        Example<StockMovement> exampleSM = Example.of(searchSM, ignoringExampleMatcher);
        return stockMovementRepository.findOne(exampleSM).isPresent();
    }
}
