package com.jsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsv.models.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, String> {

}