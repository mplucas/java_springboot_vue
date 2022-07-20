package com.jsv.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		
	@GetMapping(path="/getAll")
	public List<Product> getProducts(){
		return productRepository.findAll();
	}

	@PostMapping(path="/save")
	public void saveProduct(@RequestBody Product product){
		productRepository.save(product);
	}

	@DeleteMapping(path="/delete")
	public void deleteProduct(@RequestBody Product product){
		productRepository.delete(product);
	}
}
