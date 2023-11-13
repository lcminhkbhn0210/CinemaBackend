package com.example.cinema.management.controllers;

import com.example.cinema.management.dto.BuyProductDTO;
import com.example.cinema.management.dto.ProductRequestDTO;
import com.example.cinema.management.dto.ProductResponseDTO;
import com.example.cinema.management.model.BuyProduct;
import com.example.cinema.management.model.Product;
import com.example.cinema.management.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return new ResponseEntity<>(productService.createProduct(productRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable("id") long id){
        return new ResponseEntity<>(productService.deleteProduct(id),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductRequestDTO productRequestDTO, @PathVariable("id") long id){
        return new ResponseEntity<>(productService.updateProduct(productRequestDTO, id),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id){
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductByName(@RequestParam String name){
        return productService.getListProductByName(name);
    }

    @PostMapping("/buyProducts")
    public List<BuyProduct> createBuyProduct(@RequestBody List<BuyProductDTO> buyProductDTOS){
        return productService.createBuyProducts(buyProductDTOS);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }
}
