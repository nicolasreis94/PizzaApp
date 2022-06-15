package com.pizzariaapp.pizzaapp.repository;

import com.pizzariaapp.pizzaapp.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
}
