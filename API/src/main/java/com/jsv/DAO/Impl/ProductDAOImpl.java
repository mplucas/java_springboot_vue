package com.jsv.DAO.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.jsv.DAO.ProductDAO;
import com.jsv.models.Product;
import com.jsv.models.StockMovement;
import com.jsv.models.Product.ProductType;
import com.jsv.repository.ProductRepository;
import com.jsv.repository.StockMovementRepository;

public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;
    private final StockMovementRepository stockMovementRepository;

    public ProductDAOImpl(ProductRepository productRepository, StockMovementRepository stockMovementRepository) {
        this.productRepository = productRepository;
        this.stockMovementRepository = stockMovementRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);

    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public List<Product> getProductsBy(ProductType productType) {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getType() == productType)
                .toList();
    }

    @Override
    public List<StockMovement> getStockMovementsBy(ProductType productType) {
        List<String> productIDs = getProductsBy(productType).stream()
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
}
