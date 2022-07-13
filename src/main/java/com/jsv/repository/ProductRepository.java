package com.jsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsv.models.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

}
