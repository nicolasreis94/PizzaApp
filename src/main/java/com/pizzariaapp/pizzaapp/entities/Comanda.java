package com.pizzariaapp.pizzaapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Data
@AllArgsConstructor
public class Comanda {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private long numero;

    private int numeroMesa;

    private int clientes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comanda")
    @JsonManagedReference
    private List<Item> items;    // variavel product que retorna atributos da classe Product..
    private double valorTotal;
    private double valorDividido;
    private Boolean status;

    public Comanda (){
        this.numero = gerarNumero();
    }
    public int getClientes(){
        if(this.clientes < 1){
            return 1;
        }
        return this.clientes;
    }
    private long gerarNumero() {
        Random novoNumero = new Random();
        return novoNumero.nextLong(1,1000);
    }

    public Boolean isActive (){
        return this.getStatus();
    }


}

