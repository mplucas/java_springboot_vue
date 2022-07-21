package com.jsv.DAO;

import java.util.List;
import java.util.Optional;

import com.jsv.models.Product;
import com.jsv.models.StockMovement;
import com.jsv.models.Product.ProductType;

public interface ProductDAO {
    public List<Product> findAllProducts();

    public void saveProduct(Product product);

    public void deleteProduct(Product product);

    public List<Product> getProductsBy(ProductType productType);

    public List<StockMovement> getStockMovementsBy(ProductType productType);

    public List<StockMovement> getStockMovementsBy(String productID);

    public Optional<Product> findProductByID(String productID);
}
