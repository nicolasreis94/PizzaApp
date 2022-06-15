package com.pizzariaapp.pizzaapp.service;

import com.pizzariaapp.pizzaapp.entities.Product;
import com.pizzariaapp.pizzaapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void salvarProduto(Product product) {
        productRepository.save(product);
    }

    public List<Product> productList(String name) {
        if (!productRepository.findByNameContains(name).isEmpty()) {
            return productRepository.findByNameContains(name);
        }
        return null;
    }

    public ResponseEntity editProduct(Product product) {
        Product produto = productRepository.findByCodigoProduto(product.getCodigoProduto());
        if (produto != null) {
            produto.setId(produto.getId());
            return ResponseEntity.ok(productRepository.save(produto));
        }
        return ResponseEntity.ok("produto não encontrado!");
    }
    public List<Product> productList(int numero){
       return productRepository.findByCategoriaNumero(numero);
    }
}
