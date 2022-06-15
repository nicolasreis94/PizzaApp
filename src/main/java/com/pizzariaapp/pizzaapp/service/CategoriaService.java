package com.pizzariaapp.pizzaapp.service;

import com.pizzariaapp.pizzaapp.entities.Categoria;
import com.pizzariaapp.pizzaapp.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private Categoria categoria;
    @Autowired
    private CategoriaRepository categoriaRepository;


    public List<Categoria> categoriaList() {
        return categoriaRepository.findAll();
    }

    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public ResponseEntity edit(Categoria categoria) {

        Categoria categoria1 = categoriaRepository.findByNumero(categoria.getNumero());
        if(categoria1 != null){
            categoria.setId(categoria1.getId());
            categoriaRepository.save(categoria);
            return ResponseEntity.ok(categoria1);
        }
        return ResponseEntity.ok("CATEGORIA NÃO ENCONTADA!");

    }
    public ResponseEntity editById (Long id,Categoria categoria){
        if(categoriaRepository.existsById(id)){
            categoria.setId(id);
            categoriaRepository.save(categoria);
            return ResponseEntity.ok(categoria);
        }
        return ResponseEntity.ok("Categoria não encontrada");
    }
}
