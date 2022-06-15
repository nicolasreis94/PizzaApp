package com.pizzariaapp.pizzaapp.controllers;


import com.pizzariaapp.pizzaapp.entities.ItemRequest;
import com.pizzariaapp.pizzaapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody ItemRequest itemRequest){
        try {
            itemService.salvar(itemRequest);
           return ResponseEntity.ok("produto adicionado a comanda com sucesso");
        }catch (Exception exception){
            System.out.println("Erro: "+ exception.getMessage());
            return ResponseEntity.ok("Erro ao adicionar produto a comanda");
        }
    }
}
