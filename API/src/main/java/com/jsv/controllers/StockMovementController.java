package com.jsv.controllers;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsv.models.StockMovement;
import com.jsv.models.StockMovement.StockMovementType;
import com.jsv.repository.StockMovementRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/stockMovement")
public class StockMovementController {

    private final StockMovementRepository stockMovementRepository;

    public StockMovementController(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    @GetMapping(path = "/getAll")
    public List<StockMovement> GetStockMovements() {
        return stockMovementRepository.findAll();
    }

    @PostMapping(path = "/save")
    public void SaveStockMovement(@RequestBody StockMovement stockMovement) throws Exception {
        stockMovement.refreshStockMovementID();
        ValidateNewStockMovement(stockMovement);
        stockMovementRepository.save(stockMovement);
    }

    private void ValidateNewStockMovement(StockMovement stockMovement) throws Exception {
        ValidateIfExists(stockMovement);
        ValidateIfHasStockQuantityIfNecessary(stockMovement);
    }

    private void ValidateIfExists(StockMovement stockMovement) throws Exception {
        if (!stockMovementRepository.findById(stockMovement.getStockMovementID()).isEmpty())
            throw new Exception("Movimentação já existe.");
    }

    private void ValidateIfHasStockQuantityIfNecessary(StockMovement stockMovement) throws Exception {
        if (stockMovement.getType() != StockMovementType.Saída)
            return;
        StockMovement searchSM = new StockMovement();
        searchSM.setProductID(stockMovement.getProductID());
        List<StockMovement> stockMovementsByProductID = stockMovementRepository.findAll(Example.of(searchSM));
        double productQuantityInStock = stockMovementsByProductID.stream()
                .mapToDouble(sm -> sm.getQuantityMoved() * (sm.getType() == StockMovementType.Saída ? -1 : 1))
                .sum();
        if (productQuantityInStock - stockMovement.getQuantityMoved() < 0)
            throw new Exception("Saída inválida, sem estoque para o produto.");
    }

    @DeleteMapping(path = "/delete")
    public void DeleteStockMovement(@RequestBody StockMovement stockMovement) {
        stockMovement.refreshStockMovementID();
        stockMovementRepository.delete(stockMovement);
    }

    @ExceptionHandler()
    public void handleException() {
        
    }
}
