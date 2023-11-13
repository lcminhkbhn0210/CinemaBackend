package com.example.cinema.management.services;

import com.example.cinema.management.dto.BuyProductDTO;
import com.example.cinema.management.dto.ProductRequestDTO;
import com.example.cinema.management.dto.ProductResponseDTO;
import com.example.cinema.management.model.BuyProduct;
import com.example.cinema.management.model.Product;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO deleteProduct(long id);
    ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, long id);
    List<Product> getListProductByName(String name);
    Product getProductById(long id);

    List<BuyProduct> createBuyProducts(List<BuyProductDTO> buyProductDTOS);

    List<Product> getAllProduct();
}
