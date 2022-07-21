package com.jsv.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsv.DTO.ProductBalanceSummaryDTO;
import com.jsv.DTO.ProductTypeSummaryDTO;
import com.jsv.models.Product;
import com.jsv.models.StockMovement;
import com.jsv.models.Product.ProductType;
import com.jsv.models.StockMovement.StockMovementType;
import com.jsv.repository.ProductRepository;
import com.jsv.repository.StockMovementRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/product")
public class ProductController {

	private final ProductRepository productRepository;
	private final StockMovementRepository stockMovementRepository;

	public ProductController(ProductRepository productRepository, StockMovementRepository stockMovementRepository) {
		this.productRepository = productRepository;
		this.stockMovementRepository = stockMovementRepository;
	}

	@GetMapping(path = "/getAll")
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@GetMapping(path = "/getProductTypeSummary")
	public List<ProductTypeSummaryDTO> getProductTypeSummary() {
		List<ProductTypeSummaryDTO> productTypeSummaryDTOs = new ArrayList<ProductTypeSummaryDTO>();
		for (ProductType productType : ProductType.values()) {
			productTypeSummaryDTOs.add(getProductTypeSummaryFor(productType));
		}
		return productTypeSummaryDTOs;
	}

	private ProductTypeSummaryDTO getProductTypeSummaryFor(ProductType productType) {
		List<Product> productsByType = getProductsBy(productType);
		double sellQuantity = getStockMovementsBy(productType).stream()
				.filter(sm -> sm.getType() == StockMovementType.Saída)
				.mapToDouble(sm -> sm.getQuantityMoved())
				.sum();
		double currentQuantity = productsByType.stream()
				.mapToDouble(p -> p.getStockQuantity())
				.sum();
		return new ProductTypeSummaryDTO(productType, sellQuantity, currentQuantity);
	}

	private List<Product> getProductsBy(ProductType productType) {
		return productRepository.findAll()
				.stream()
				.filter(p -> p.getType() == productType)
				.toList();
	}

	private List<StockMovement> getStockMovementsBy(ProductType productType) {
		List<String> productIDs = getProductsBy(productType).stream()
				.map(p -> p.getProductID())
				.distinct()
				.collect(Collectors.toList());
		List<StockMovement> stockMovements = new ArrayList<StockMovement>();
		for (String productID : productIDs) {
			stockMovements.addAll(getStockMovementsBy(productID));
		}
		return stockMovements;
	}

	private List<StockMovement> getStockMovementsBy(String productID) {
		ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
				.withIgnorePaths("sellDate", "type", "sellPrice", "quantityMoved");
		StockMovement searchSM = new StockMovement();
		searchSM.setProductID(productID);
		Example<StockMovement> exampleSM = Example.of(searchSM, ignoringExampleMatcher);
		return stockMovementRepository.findAll(exampleSM);
	}

	@GetMapping(path = "/getProductBalanceSummary")
	public List<ProductBalanceSummaryDTO> getProductBalanceSummary() {
		List<ProductBalanceSummaryDTO> productBalanceSummary = new ArrayList<ProductBalanceSummaryDTO>();
		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			productBalanceSummary.add(getProductBalanceSummaryFor(product.getProductID()));
		}
		return productBalanceSummary;
	}

	private ProductBalanceSummaryDTO getProductBalanceSummaryFor(String productID) {
		List<StockMovement> stockMovementsByProduct = getStockMovementsBy(productID);
		double sellQuantity = stockMovementsByProduct.stream()
				.filter(sm -> sm.getType() == StockMovementType.Saída)
				.mapToDouble(sm -> sm.getQuantityMoved())
				.sum();
		double totalProfit = stockMovementsByProduct.stream()
				.mapToDouble(sm -> sm.getQuantityMoved() * sm.getSellPrice()
						* (sm.getType() == StockMovementType.Saída ? 1 : -1))
				.sum();
		return new ProductBalanceSummaryDTO(productID, sellQuantity, totalProfit);
	}

	@PostMapping(path = "/save")
	public void saveProduct(@RequestBody Product product) {
		productRepository.save(product);
	}

	@DeleteMapping(path = "/delete")
	public void deleteProduct(@RequestBody Product product) {
		productRepository.delete(product);
	}
}
