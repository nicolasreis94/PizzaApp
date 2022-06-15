package com.pizzariaapp.pizzaapp.controllers;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pizzariaapp.pizzaapp.entities.Categoria;
import com.pizzariaapp.pizzaapp.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listar")
    public List<Categoria> list (){
       try{
           return categoriaService.categoriaList();
       }catch (Exception exception){
           System.out.println("ERRO: "+ exception.getMessage());
           return null;
       }
    }
    @PostMapping("/salvar")
    public Categoria salvar(@RequestBody Categoria categoria){
       try{
           return categoriaService.salvarCategoria(categoria);
       }catch (Exception exception){
           System.out.println("ERRO: " + exception.getMessage());
           return null;
       }
    }
    @PutMapping("/editar/")
    public ResponseEntity editar (@RequestBody Categoria categoria){
        try{
            return categoriaService.edit(categoria);
        }catch (Exception exception){
            System.out.println("ERRO: " + exception.getMessage());
            return ResponseEntity.ok("ERRO AO EDITAR CATEGORIA");
        }
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity editById (@PathVariable Long id,
                                    @RequestBody Categoria categoria){
        try{
            return categoriaService.editById(id,categoria);
        }catch (Exception exception){
            System.out.println("ERRO: " + exception.getMessage());
            return ResponseEntity.ok("ERRO AO EDITAR CATEGORIA");
        }

    }
}
