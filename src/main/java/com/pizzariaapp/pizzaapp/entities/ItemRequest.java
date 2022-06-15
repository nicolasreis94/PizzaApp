package com.pizzariaapp.pizzaapp.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {

    private long codigoProduto;
    private long codigoComanda;
    private int quantidade;
    private String observacao;
}
