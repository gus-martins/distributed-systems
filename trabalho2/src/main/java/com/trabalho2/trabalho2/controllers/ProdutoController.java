package com.trabalho2.trabalho2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.trabalho2.trabalho2.entities.Produto;
import com.trabalho2.trabalho2.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public ResponseEntity<Produto[]> getProdutos() {
        Produto[] produtos = produtoService.getProdutos();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.addProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable(name = "id") long id) {
        boolean deleted = produtoService.deletarProduto(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable(name = "id") long id, @RequestBody Produto produto) {
        Produto produtoTrocado = produtoService.updateProduto(id, produto);
        if (produtoTrocado != null) {
            return ResponseEntity.ok(produtoTrocado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
