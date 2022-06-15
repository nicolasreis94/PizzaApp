package com.pizzariaapp.pizzaapp.repository;

import com.pizzariaapp.pizzaapp.entities.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda,Long> {
    Comanda findByNumero(Long numero);
    List<Comanda> findByNumeroMesa (int numeroMesa);

}
