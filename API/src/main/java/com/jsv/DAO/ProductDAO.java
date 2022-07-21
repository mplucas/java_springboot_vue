package com.jsv.DAO;

import java.util.List;
import java.util.Optional;

import com.jsv.enums.ProductTypeEnum;
import com.jsv.models.Product;

public interface ProductDAO {
    public List<Product> findAllProducts();

    public void saveProduct(Product product);

    public void deleteProduct(Product product);

    public List<Product> getProductsBy(ProductTypeEnum productType);

    public Optional<Product> findProductByID(String productID);
}
