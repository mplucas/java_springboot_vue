package com.jsv.controllers;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsv.DAO.Impl.ProductDAOImpl;
import com.jsv.DAO.Impl.StockMovementDAOImpl;
import com.jsv.models.Product;
import com.jsv.models.StockMovement;
import com.jsv.models.StockMovement.StockMovementType;
import com.jsv.repository.ProductRepository;
import com.jsv.repository.StockMovementRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/stockMovement")
public class StockMovementController {

    private final StockMovementDAOImpl stockMovementDAO;
    private final ProductDAOImpl productDAO;

    public StockMovementController(StockMovementRepository stockMovementRepository,
            ProductRepository productRepository) {
        stockMovementDAO = new StockMovementDAOImpl(productRepository, stockMovementRepository);
        productDAO = new ProductDAOImpl(productRepository, stockMovementRepository);
    }

    @GetMapping(path = "/getAll")
    public List<StockMovement> getStockMovements() {
        return stockMovementDAO.findAllStockMovements();
    }

    @PostMapping(path = "/save")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveStockMovement(@RequestBody StockMovement stockMovement) throws Exception {
        validateNewStockMovement(stockMovement);
        stockMovementDAO.saveStockMovement(stockMovement);
        updateProductQuantity(stockMovement);
    }

    private void validateNewStockMovement(StockMovement stockMovement) throws Exception {
        validateIfExists(stockMovement);
        validateProduct(stockMovement);
        validateIfHasStockQuantityIfNecessary(stockMovement);
    }

    private void validateIfExists(StockMovement stockMovement) throws Exception {
        if (stockMovementDAO.existsStockMovement(stockMovement))
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
