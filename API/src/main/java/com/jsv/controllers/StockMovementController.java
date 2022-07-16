package com.jsv.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsv.models.StockMovement;
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
    public void SaveStockMovement(@RequestBody StockMovement stockMovement) {
        stockMovement.refreshStockMovementID();
        stockMovementRepository.save(stockMovement);
    }

    @DeleteMapping(path = "/delete")
    public void DeleteStockMovement(@RequestBody StockMovement stockMovement) {
        stockMovement.refreshStockMovementID();
        stockMovementRepository.delete(stockMovement);
    }
}
