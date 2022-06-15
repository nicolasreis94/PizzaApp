package com.pizzariaapp.pizzaapp.service;

import com.pizzariaapp.pizzaapp.entities.Comanda;
import com.pizzariaapp.pizzaapp.entities.Item;
import com.pizzariaapp.pizzaapp.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComandaService {
    @Autowired
    private ComandaRepository comandaRepository;
    private Comanda comanda;

    public List<Comanda> todasComandas() {
        return comandaRepository.findAll();
    }

    public ResponseEntity abrirComanda(Integer numeroMesa) {
        if(numeroMesa!= null && numeroMesa >= 1 && numeroMesa <=100){
            Comanda novaComanda = new Comanda();
            novaComanda.setStatus(true);
            novaComanda.setNumeroMesa(numeroMesa);
           return ResponseEntity.ok(comandaRepository.save(novaComanda));
        }
        return ResponseEntity.ok("NUMERO DE MESA INVALIDO");
    }

    public Comanda fecharComanda(Long numeroComanda) {              //não consigo usar um response entity aqui. pq usei este metodo la em baixo
        Comanda fechamento = comandaRepository.findByNumero(numeroComanda);
        if (fechamento != null && fechamento.isActive()) {
            double valorTotal = 0;
            for (Item itemSelecionado : fechamento.getItems()) {                   //for laço de repetição(CLASSE objeto ""receber"" fechamento
                valorTotal += itemSelecionado.getTotal();
            }
            fechamento.setValorTotal(valorTotal);
            fechamento.setValorDividido(valorTotal / fechamento.getClientes());
            fechamento.setStatus(false);
            return comandaRepository.save(fechamento);
        }
        return fechamento;
    }

    public ResponseEntity reabrirComanda(Long numeroComanda) {
        Comanda reabrir = comandaRepository.findByNumero(numeroComanda);
        if(reabrir !=null){
            reabrir.setStatus(true);
          return ResponseEntity.ok(comandaRepository.save(reabrir));
        }
        return ResponseEntity.ok("Comanda não encontrada!");
    }

    public void somarComandas(Long numeroComanda1, Long numeroComanda2) {
        Comanda comanda1 = comandaRepository.findByNumero(numeroComanda1);
        Comanda comanda2 = comandaRepository.findByNumero(numeroComanda2);
        Comanda comandasSomadas = new Comanda();
        if (comanda1 != null && comanda2 != null) {

            double valortotal1 = 0;
            for (Item itemselecionado : comanda1.getItems()) {
                valortotal1 += itemselecionado.getTotal();
            }

            double valortotal2 = 0;
            for (Item itemselecionado2 : comanda2.getItems()) {
                valortotal2 += itemselecionado2.getTotal();
            }

            comanda1.setStatus(false);
            comanda2.setStatus(false);
            comanda1.setValorTotal(valortotal1);
            comanda2.setValorTotal(valortotal2);
            comandasSomadas.setValorTotal(valortotal1 + valortotal2);
            comandasSomadas.setClientes(comanda1.getClientes() + comanda2.getClientes());
            ;
            comandasSomadas.setValorDividido(comandasSomadas.getValorTotal() / comandasSomadas.getClientes());
            comandasSomadas.setNumeroMesa(comanda1.getNumeroMesa());
            comandasSomadas.setStatus(false);
            comandaRepository.save(comanda1);
            comandaRepository.save(comanda2);
            comandaRepository.save(comandasSomadas);
        }
    }

    public Comanda somarMultiplasComandas(List<Long> numeroComandaList) {

        double valorTotal = 0;
        int numeroPessoa = 0;
        for (Long numeroComanda : numeroComandaList) {
            Comanda comandaRecebida = fecharComanda(numeroComanda);
            if(comandaRecebida != null) {
                valorTotal += comandaRecebida.getValorTotal();
                numeroPessoa += comandaRecebida.getClientes();
            }
        }

        Comanda comandaFinal = new Comanda();
        comandaFinal.setClientes(numeroPessoa);
        comandaFinal.setValorTotal(valorTotal);
        comandaFinal.setValorDividido(comandaFinal.getValorTotal() / comandaFinal.getClientes());
        comandaFinal.setStatus(false);
        comandaRepository.save(comandaFinal);
        return comandaFinal;
    }

    public ResponseEntity alterarClientes(Long numeroComanda, int numeroClientes) {
        Comanda comanda = comandaRepository.findByNumero(numeroComanda);
        if(comanda != null){
            comanda.setClientes(numeroClientes);
            return ResponseEntity.ok("Clientes adicionados com sucesso");
        }
        return ResponseEntity.ok("COMANDA NÃO ENCONTRADA");

    }

    public ResponseEntity<?> exibirComanda(Long numeroComanda) {
        Comanda comanda = comandaRepository.findByNumero(numeroComanda);
        if(comanda != null){
            comanda.setValorDividido(comanda.getValorTotal() / comanda.getClientes());
            return ResponseEntity.ok(comanda);
        } return ResponseEntity.ok("Comanda não encontrada");

    }
    public ResponseEntity listarComandasPorMesa (int mesa){
        List<Comanda> comandaList = comandaRepository.findByNumeroMesa(mesa);
        if(comandaList.isEmpty()){
            return ResponseEntity.ok("NENHUMA COMANDA ABERTA NESTA MESA");
        }
        return ResponseEntity.ok(comandaList);

    }
}

