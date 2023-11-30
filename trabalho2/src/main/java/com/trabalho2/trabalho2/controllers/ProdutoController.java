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

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable int id) {
        Produto produto = produtoService.getProdutoById(id);

        return ResponseEntity.ok(produto);
    }

    @PostMapping("")
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.addProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable int id) {
        boolean deleted = produtoService.deletarProduto(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{nome}")
    public ResponseEntity<Produto> updateProduto(@PathVariable String nome, @RequestBody Produto produto) {
        Produto produtoTrocado = produtoService.updateProduto(nome, produto);
        if (produtoTrocado != null) {
            return ResponseEntity.ok(produtoTrocado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
