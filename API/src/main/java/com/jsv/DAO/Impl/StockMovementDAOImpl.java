package com.jsv.DAO.Impl;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.jsv.DAO.StockMovementDAO;
import com.jsv.models.Product;
import com.jsv.models.StockMovement;
import com.jsv.models.StockMovement.StockMovementType;
import com.jsv.repository.ProductRepository;
import com.jsv.repository.StockMovementRepository;

public class StockMovementDAOImpl implements StockMovementDAO {

    private final StockMovementRepository stockMovementRepository;
    private final ProductDAOImpl productDAO;

    public StockMovementDAOImpl(ProductRepository productRepository, StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productDAO = new ProductDAOImpl(productRepository, stockMovementRepository);
    }

    @Override
    public List<StockMovement> findAllStockMovements() {
        return stockMovementRepository.findAll();
    }

    @Override
    public void saveStockMovement(StockMovement stockMovement) throws Exception {
        validateNewStockMovement(stockMovement);
        stockMovementRepository.save(stockMovement);
        updateProductQuantity(stockMovement);
    }

    private void validateNewStockMovement(StockMovement stockMovement) throws Exception {
        validateIfExists(stockMovement);
        validateProduct(stockMovement);
        validateIfHasStockQuantityIfNecessary(stockMovement);
    }

    private void validateIfExists(StockMovement stockMovement) throws Exception {
        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("type", "sellPrice", "quantityMoved");
        StockMovement searchSM = new StockMovement();
        searchSM.setProductID(stockMovement.getProductID());
        searchSM.setSellDate(stockMovement.getSellDate());
        Example<StockMovement> exampleSM = Example.of(searchSM, ignoringExampleMatcher);
        if (!stockMovementRepository.findOne(exampleSM).isEmpty())
            throw new Exception("Movimentação já existe.");
    }

    private void validateProduct(StockMovement stockMovement) throws Exception {
        if (productDAO.findProductByID(stockMovement.getProductID()).isEmpty())
            throw new Exception("Produto não cadastrado.");
    }

    private void validateIfHasStockQuantityIfNecessary(StockMovement stockMovement) throws Exception {
        if (stockMovement.getType() != StockMovementType.Saída)
            return;
        double productQuantityInStock = getProductQuantityInStock(stockMovement.getProductID());
        if (productQuantityInStock - stockMovement.getQuantityMoved() < 0)
            throw new Exception("Saída inválida, sem estoque para o produto.");
    }

    private double getProductQuantityInStock(String productID) {
        Product product = productDAO.findProductByID(productID).orElse(null);
        return product.getStockQuantity();
    }

    private void updateProductQuantity(StockMovement stockMovement) {
        Product product = productDAO.findProductByID(stockMovement.getProductID()).orElse(null);
        double quantityToAdd = stockMovement.getQuantityMoved()
                * (stockMovement.getType() == StockMovementType.Saída ? -1 : 1);
        product.addStockQuantity(quantityToAdd);
        productDAO.saveProduct(product);
    }
}
