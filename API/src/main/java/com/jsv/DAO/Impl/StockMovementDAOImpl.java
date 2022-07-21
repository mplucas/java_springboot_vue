package com.jsv.DAO.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.jsv.DAO.StockMovementDAO;
import com.jsv.enums.ProductTypeEnum;
import com.jsv.models.StockMovement;
import com.jsv.repository.ProductRepository;
import com.jsv.repository.StockMovementRepository;

public class StockMovementDAOImpl implements StockMovementDAO {

    private final StockMovementRepository stockMovementRepository;
    private final ProductDAOImpl productDAO;

    public StockMovementDAOImpl(ProductRepository productRepository, StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
        productDAO = new ProductDAOImpl(productRepository);
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

    @Override
    public List<StockMovement> getStockMovementsBy(ProductTypeEnum productType) {
        List<String> productIDs = productDAO.getProductsBy(productType).stream()
                .map(p -> p.getProductID())
                .distinct()
                .collect(Collectors.toList());
        List<StockMovement> stockMovements = new ArrayList<StockMovement>();
        for (String productID : productIDs) {
            stockMovements.addAll(getStockMovementsBy(productID));
        }
        return stockMovements;
    }

    @Override
    public List<StockMovement> getStockMovementsBy(String productID) {
        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("sellDate", "type", "sellPrice", "quantityMoved");
        StockMovement searchSM = new StockMovement();
        searchSM.setProductID(productID);
        Example<StockMovement> exampleSM = Example.of(searchSM, ignoringExampleMatcher);
        return stockMovementRepository.findAll(exampleSM);
    }

    @Override
    public void deleteStockMovementsBy(String productID) {
        List<StockMovement> stockMovements = getStockMovementsBy(productID);
        for (StockMovement stockMovement : stockMovements) {
            stockMovementRepository.delete(stockMovement);
        }
    }
}
