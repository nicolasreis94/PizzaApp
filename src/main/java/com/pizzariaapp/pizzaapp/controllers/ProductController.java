package com.pizzariaapp.pizzaapp.controllers;

import com.pizzariaapp.pizzaapp.entities.Product;
import com.pizzariaapp.pizzaapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity salvarProduct(@RequestBody Product product){
        try{
            productService.salvarProduto(product);
            return ResponseEntity.ok("Produto Salvo com Sucesso!");
        }catch (Exception exception){
            System.out.println("Erro: "+ exception.getMessage());
            return   ResponseEntity.ok("Erro ao salvar produto!");
        }
    }

    @GetMapping("/listar/{productname}")
    public ResponseEntity<?> listar (@PathVariable String productname){
       return ResponseEntity.ok(productService.productList(productname));
    }
    @PostMapping("/editar")
    public ResponseEntity editarProduto (@RequestBody Product product){
        try{
            return productService.editProduct(product);

        } catch (Exception exception){
            System.out.println("Erro " + exception.getMessage());
           return ResponseEntity.ok("Erro ao editar produto");
        }
    }
    @GetMapping("/categoria/{numero}")
    public ResponseEntity produtosPorCategoria(@PathVariable int numero){
      return ResponseEntity.ok(productService.productList(numero));
    }
}


