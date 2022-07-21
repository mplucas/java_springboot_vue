package com.jsv.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsv.DAO.Impl.ProductDAOImpl;
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

	private ProductDAOImpl productDAO;

	public ProductController(ProductRepository productRepository, StockMovementRepository stockMovementRepository) {
		productDAO = new ProductDAOImpl(productRepository, stockMovementRepository);
	}

	@GetMapping(path = "/getAll")
	public List<Product> getProducts() {
		return productDAO.findAllProducts();
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
		List<Product> productsByType = productDAO.getProductsBy(productType);
		double sellQuantity = productDAO.getStockMovementsBy(productType).stream()
				.filter(sm -> sm.getType() == StockMovementType.Saída)
				.mapToDouble(sm -> sm.getQuantityMoved())
				.sum();
		double currentQuantity = productsByType.stream()
				.mapToDouble(p -> p.getStockQuantity())
				.sum();
		return new ProductTypeSummaryDTO(productType, sellQuantity, currentQuantity);
	}

	@GetMapping(path = "/getProductBalanceSummary")
	public List<ProductBalanceSummaryDTO> getProductBalanceSummary() {
		List<ProductBalanceSummaryDTO> productBalanceSummary = new ArrayList<ProductBalanceSummaryDTO>();
		List<Product> products = productDAO.findAllProducts();
		for (Product product : products) {
			productBalanceSummary.add(getProductBalanceSummaryFor(product.getProductID()));
		}
		return productBalanceSummary;
	}

	private ProductBalanceSummaryDTO getProductBalanceSummaryFor(String productID) {
		List<StockMovement> stockMovementsByProduct = productDAO.getStockMovementsBy(productID);
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
		productDAO.saveProduct(product);
	}

	@DeleteMapping(path = "/delete")
	public void deleteProduct(@RequestBody Product product) {
		productDAO.deleteProduct(product);
	}
}
