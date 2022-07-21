package com.jsv.DAO.Impl;

import java.util.List;
import java.util.Optional;
import com.jsv.DAO.ProductDAO;
import com.jsv.models.Product;
import com.jsv.models.Product.ProductType;
import com.jsv.repository.ProductRepository;

public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);

    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public List<Product> getProductsBy(ProductType productType) {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getType() == productType)
                .toList();
    }

    @Override
    public Optional<Product> findProductByID(String productID) {
        return productRepository.findById(productID);
    }
}
