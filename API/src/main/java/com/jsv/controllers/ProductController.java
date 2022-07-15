package com.jsv.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsv.models.Product;
import com.jsv.repository.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping(path="/product")
public class ProductController {
	
	private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
	@GetMapping(path="/test")
	public Product Test() {
		return new Product("1", "Produto teste", "Eletrodoméstico", 19.99, 20);
	}
	
	@GetMapping(path="/getAll")
	public List<Product> GetProducts(){
		return productRepository.findAll();
	}

	@PostMapping(path="/save")
	public void SaveProduct(Product product){
		productRepository.save(product);
	}

	@PostMapping(path="/delete")
	public void DeleteProduct(Product product){
		productRepository.delete(product);
	}
}
