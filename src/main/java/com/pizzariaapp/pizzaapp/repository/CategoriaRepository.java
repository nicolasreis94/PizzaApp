package com.pizzariaapp.pizzaapp.repository;

import com.pizzariaapp.pizzaapp.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Categoria findByNumero (Integer numero);

}
