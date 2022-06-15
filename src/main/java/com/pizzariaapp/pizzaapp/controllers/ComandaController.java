package com.pizzariaapp.pizzaapp.controllers;

import com.pizzariaapp.pizzaapp.entities.Comanda;
import com.pizzariaapp.pizzaapp.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comanda")
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    @GetMapping
    public List<Comanda> listar() {
        return comandaService.todasComandas();
    }

    @GetMapping("/exibir/{numeroComanda}")
    public ResponseEntity<?> exibir(@PathVariable long numeroComanda) {
        try {
            return comandaService.exibirComanda(numeroComanda);
        } catch (Exception exception) {
            System.out.println("ERRO:" + exception.getMessage());
           return ResponseEntity.ok("ERRO AO EXIBIR COMANDA");
        }
    }

    @PostMapping("/{numeroMesa}")
    public ResponseEntity<?> abrirComanda(@PathVariable Integer numeroMesa) {
        try {
            return comandaService.abrirComanda(numeroMesa);

        } catch (Exception exception) {
            System.out.println("Erro: " + exception.getMessage());
            return ResponseEntity.ok("Erro ao criar comanda");
        }
    }

    @GetMapping("/fechar/{numeroComanda}")
    public ResponseEntity<?> fecharComanda(@PathVariable Long numeroComanda) {
        try {
          return ResponseEntity.ok(comandaService.fecharComanda(numeroComanda));

        } catch (Exception exception) {
            System.out.println("Erro " + exception.getMessage());
            return ResponseEntity.ok("ERRO AO BUSCAR COMANDA");
        }
    }

    @PostMapping("/reabrir/{numeroComanda}")
    public ResponseEntity<?> reabrirComanda(@PathVariable Long numeroComanda) {
        return comandaService.reabrirComanda(numeroComanda);
    }

    @PostMapping("/somar/{numeroComanda1}/{numeroComanda2}")
    public ResponseEntity<?> somarComandas(@PathVariable Long numeroComanda1,
                                           @PathVariable Long numeroComanda2) {
        try {
            comandaService.somarComandas(numeroComanda1, numeroComanda2);
            return ResponseEntity.ok("Comandas Somadas com Sucesso");
        } catch (Exception exception) {
            System.out.println("Erro " + exception.getMessage());
            return ResponseEntity.ok("Erro ao somar comandas");
        }
    }

    @RequestMapping(value = "/somar/multiplas", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE})
    public ResponseEntity<?> somarMultiplas (@RequestBody List<Long> listNumerosComandas){
        try {
            return ResponseEntity.ok(comandaService.somarMultiplasComandas(listNumerosComandas));
        } catch (Exception exception){
            System.out.println("Erro " + exception.getMessage());
            return ResponseEntity.ok("Erro ao somar comandas");
        }
    }


    @PostMapping("/alterarclientes/{numeroComanda}/{numeroClientes}")
    public ResponseEntity<?> alterarClientes(@PathVariable Long numeroComanda,
                                             @PathVariable int numeroClientes) {

        try {
            return comandaService.alterarClientes(numeroComanda, numeroClientes);
        } catch (Exception exception) {
            System.out.println("Erro " + exception.getMessage());
            return ResponseEntity.ok("Erro ao adicionar clientes");
        }
    }
    @GetMapping("/listar/mesa/{numeromesa}")
    public ResponseEntity<String> listarComandasMesa (@PathVariable int numeromesa){
        try{
            return comandaService.listarComandasPorMesa(numeromesa);
        }catch (Exception exception){
            System.out.println("ERRO: " + exception.getMessage());
            return ResponseEntity.ok("ERRO");
        }
    }

}
