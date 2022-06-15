package com.pizzariaapp.pizzaapp.service;

import com.pizzariaapp.pizzaapp.entities.Comanda;
import com.pizzariaapp.pizzaapp.entities.Item;
import com.pizzariaapp.pizzaapp.entities.ItemRequest;
import com.pizzariaapp.pizzaapp.entities.Product;
import com.pizzariaapp.pizzaapp.repository.ComandaRepository;
import com.pizzariaapp.pizzaapp.repository.ItemRepository;
import com.pizzariaapp.pizzaapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ComandaRepository comandaRepository;

    public void salvar (ItemRequest itemRequest){
        Product produtoSelecionado = productRepository.findByCodigoProduto(itemRequest.getCodigoProduto());
        Item novoItem = new Item();
        novoItem.setProduct(produtoSelecionado);
        Comanda comandaSelecionada = comandaRepository.findByNumero(itemRequest.getCodigoComanda());
        novoItem.setComanda(comandaSelecionada);
        novoItem.setQuantidade(itemRequest.getQuantidade());
        novoItem.setObservacao(itemRequest.getObservacao());
        novoItem.setTotal(itemRequest.getQuantidade() * produtoSelecionado.getValor());
        itemRepository.save(novoItem);
    }

}
