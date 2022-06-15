package com.pizzariaapp.pizzaapp.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;        // privado que espera retornar um numero int // id do produto
    private long codigoProduto;
    private String name;   //privado espera retornar uma string / nome do produto
    private double valor;  // privado que espera retornar um numero double //valor do produto
    @ManyToOne
    @JoinColumn
    private Categoria categoria;
//
}
