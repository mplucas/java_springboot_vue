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

import com.jsv.DAO.Impl.StockMovementDAOImpl;
import com.jsv.models.StockMovement;
import com.jsv.repository.ProductRepository;
import com.jsv.repository.StockMovementRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/stockMovement")
public class StockMovementController {

    private StockMovementDAOImpl stockMovementDAO;

    public StockMovementController(StockMovementRepository stockMovementRepository,
            ProductRepository productRepository) {
        stockMovementDAO = new StockMovementDAOImpl(productRepository, stockMovementRepository);
    }

    @GetMapping(path = "/getAll")
    public List<StockMovement> getStockMovements() {
        return stockMovementDAO.findAllStockMovements();
    }

    @PostMapping(path = "/save")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveStockMovement(@RequestBody StockMovement stockMovement) throws Exception {
        stockMovementDAO.saveStockMovement(stockMovement);
    }
}
