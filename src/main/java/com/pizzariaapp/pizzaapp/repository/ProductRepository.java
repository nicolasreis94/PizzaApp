package com.pizzariaapp.pizzaapp.repository;

import com.pizzariaapp.pizzaapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findByCodigoProduto(Long codigoProduto);
    List<Product> findByNameContains(String name);
    List<Product> findByCategoriaNumero (int numero);

}
