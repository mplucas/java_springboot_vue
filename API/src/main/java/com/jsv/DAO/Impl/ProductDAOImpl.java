package com.jsv.DAO.Impl;

import java.util.List;

import com.jsv.DAO.ProductDAO;
import com.jsv.DTO.ProductBalanceSummaryDTO;
import com.jsv.DTO.ProductTypeSummaryDTO;
import com.jsv.models.Product;
import com.jsv.repository.ProductRepository;

public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductTypeSummaryDTO> getProductTypeSummary() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProductBalanceSummaryDTO> getProductBalanceSummary() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveProduct(Product product) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteProduct(Product product) {
        // TODO Auto-generated method stub

    }

}
