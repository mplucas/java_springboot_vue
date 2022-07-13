package com.jsv.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsv.models.Product;

@RestController
public class ProductController {
	
	@GetMapping(path="/test")
	public Product Test() {
		return new Product("1", "Produto teste", "Eletrodoméstico", 19.99, 20);
	}
}
