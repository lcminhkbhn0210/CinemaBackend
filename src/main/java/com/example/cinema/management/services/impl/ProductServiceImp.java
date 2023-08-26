package com.example.cinema.management.services.impl;

import com.example.cinema.management.dto.BuyProductDTO;
import com.example.cinema.management.dto.ProductRequestDTO;
import com.example.cinema.management.dto.ProductResponseDTO;
import com.example.cinema.management.model.BuyProduct;
import com.example.cinema.management.model.GiftProduct;
import com.example.cinema.management.model.Product;
import com.example.cinema.management.model.SellProduct;
import com.example.cinema.management.repositories.BuyProductRepository;
import com.example.cinema.management.repositories.ProductRepository;
import com.example.cinema.management.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BuyProductRepository buyProductRepository;
    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        if (!productRepository.findByName(productRequestDTO.getName()).isPresent()) {

            if (productRequestDTO.getType().toUpperCase().equals("SELL")) {
                SellProduct product = new SellProduct();
                product.setPrice(productRequestDTO.getPrice());
                product.setName(productRequestDTO.getName());
                product.setDes(productRequestDTO.getDes());
                product.setAmount(productRequestDTO.getAmount());
                product.setImg(productRequestDTO.getImg());
                productRepository.save(product);
                return new ProductResponseDTO("Tao san pham thanh cong", "202: Created");
            } else {
                GiftProduct product = new GiftProduct();
                product.setName(productRequestDTO.getName());
                product.setDes(productRequestDTO.getDes());
                product.setAmount(productRequestDTO.getAmount());
                product.setImg(productRequestDTO.getImg());
                productRepository.save(product);
                return new ProductResponseDTO("Tao san pham thanh cong", "202: Created");
            }
        }
        return new ProductResponseDTO("San pham da ton tai", "401: Not Create");
    }
    @Override
    public ProductResponseDTO deleteProduct(long id) {
        if (productRepository.findById(id).isPresent()){
            return new ProductResponseDTO("Xoa san pham thanh cong","200: 0k");
        }
        return new ProductResponseDTO("Xoa san pham that bai. San pham khong ton tai","404: Not Found");
    }

    @Override
    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, long id) {
        Optional<Product> productDb = productRepository.findById(id);
        if(productDb.isPresent()){
            if(productRequestDTO.getType().toUpperCase().equals("SELL")){
                SellProduct sellProduct = new SellProduct();
                sellProduct.setPrice(productRequestDTO.getPrice());
                sellProduct.setName(productRequestDTO.getName());
                sellProduct.setDes(productRequestDTO.getDes());
                sellProduct.setAmount(productRequestDTO.getAmount());
                sellProduct.setImg(productRequestDTO.getImg());
                sellProduct.setId(id);
                productRepository.save(sellProduct);
                return new ProductResponseDTO("Cap nhat san pham mua thanh cong", "200: OK");
            }
            else {
                GiftProduct giftProduct = new GiftProduct();
                giftProduct.setId(id);
                giftProduct.setName(productRequestDTO.getName());
                giftProduct.setDes(productRequestDTO.getDes());
                giftProduct.setAmount(productRequestDTO.getAmount());
                giftProduct.setImg(productRequestDTO.getImg());
                productRepository.save(giftProduct);
                return new ProductResponseDTO("Cap nhat san pham tang thanh cong", "200: OK");
            }
        }
        return new ProductResponseDTO("San pham  khong ton tai","404: Not Found");
    }

    @Override
    public List<Product> getListProductByName(String name) {
        return productRepository.getProductsByName(name);
    }

    @Override
    public Product getProductById(long id) {
        if(productRepository.findById(id).isPresent()){
            return productRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<BuyProduct> createBuyProducts(List<BuyProductDTO> buyProductDTOS) {
        List<BuyProduct> buyProducts = new ArrayList<>();
        for (BuyProductDTO buyProductDTO:buyProductDTOS){
            BuyProduct buyProduct = new BuyProduct();
            buyProduct.setSellProduct(productRepository.getSellProductByID(buyProductDTO.getProductID()));
            buyProduct.setAmount(buyProductDTO.getAmount());
            buyProducts.add(buyProduct);
        }
        return buyProductRepository.saveAll(buyProducts);
    }


}
