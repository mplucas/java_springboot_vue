package com.jsv.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsv.models.Product;
import com.jsv.repository.ProductRepository;

@RestController
public class ProductController {
	
	private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
	@GetMapping(path="/test")
	public Product Test() {
		return new Product("1", "Produto teste", "Eletrodoméstico", 19.99, 20);
	}
	
	@GetMapping(path="/products")
	public List<Product> GetProducts(){
		return productRepository.findAll();
	}
}
