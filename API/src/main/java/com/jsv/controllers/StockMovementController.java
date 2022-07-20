package com.jsv.controllers;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsv.models.Product;
import com.jsv.models.StockMovement;
import com.jsv.models.StockMovement.StockMovementType;
import com.jsv.repository.ProductRepository;
import com.jsv.repository.StockMovementRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/stockMovement")
public class StockMovementController {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public StockMovementController(StockMovementRepository stockMovementRepository,
            ProductRepository productRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/getAll")
    public List<StockMovement> getStockMovements() {
        return stockMovementRepository.findAll();
    }

    @PostMapping(path = "/save")
    public void saveStockMovement(@RequestBody StockMovement stockMovement) throws Exception {
        validateNewStockMovement(stockMovement);
        stockMovementRepository.save(stockMovement);
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
        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("description", "type", "buyPrice", "stockQuantity");
        Product searchProduct = new Product();
        searchProduct.setProductID(stockMovement.getProductID());
        Example<Product> exampleProduct = Example.of(searchProduct, ignoringExampleMatcher);
        if (productRepository.findOne(exampleProduct).isEmpty())
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
        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("sellDate", "type", "sellPrice", "quantityMoved");
        StockMovement searchSM = new StockMovement();
        searchSM.setProductID(productID);
        Example<StockMovement> exampleSM = Example.of(searchSM, ignoringExampleMatcher);
        List<StockMovement> stockMovementsByProductID = stockMovementRepository.findAll(exampleSM);
        return stockMovementsByProductID.stream()
                .mapToDouble(sm -> sm.getQuantityMoved() * (sm.getType() == StockMovementType.Saída ? -1 : 1))
                .sum();
    }

    @DeleteMapping(path = "/delete")
    public void deleteStockMovement(@RequestBody StockMovement stockMovement) {
        stockMovementRepository.delete(stockMovement);
    }
}
