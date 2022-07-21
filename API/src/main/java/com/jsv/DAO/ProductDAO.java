package com.jsv.DAO;

import java.util.List;

import com.jsv.DTO.ProductBalanceSummaryDTO;
import com.jsv.DTO.ProductTypeSummaryDTO;
import com.jsv.models.Product;

public interface ProductDAO {
    public List<Product> getProducts();

    public List<ProductTypeSummaryDTO> getProductTypeSummary();

    public List<ProductBalanceSummaryDTO> getProductBalanceSummary();

    public void saveProduct(Product product);

    public void deleteProduct(Product product);
}
