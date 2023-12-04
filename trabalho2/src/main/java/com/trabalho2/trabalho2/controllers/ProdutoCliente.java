package com.trabalho2.trabalho2.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.trabalho2.trabalho2.entities.Produto;

public class ProdutoCliente {

    private final String URL = "http://localhost:8080";
    private final RestTemplate restTemplate;

    public ProdutoCliente() {
        this.restTemplate = new RestTemplate();
    }

    // listar produtos
    public ResponseEntity<Produto[]> getProdutos() {
        String url = URL + "/produtos/listar";
        return restTemplate.getForEntity(url, Produto[].class);
    }

    // adicionar produto
    public ResponseEntity<Produto> createProduto(Produto produto) {
        String url = URL + "/produtos/adicionar";
        return restTemplate.postForEntity(url, produto, Produto.class);
    }

    // deletar produto
    public ResponseEntity<Void> deleteProduto(long id) {
        String url = URL + "/produtos/deletar/" + id;
        return restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    }

    // atualizar produto
    public ResponseEntity<Produto> updateProduto(long id, Produto produto) {
        String url = URL + "/produtos/update/{id}"; // Usando {id} como um placeholder
        Map<String, Long> urlVariables = new HashMap<>();
        urlVariables.put("id", id);

        HttpEntity<Produto> request = new HttpEntity<>(produto);
        return restTemplate.exchange(url, HttpMethod.PUT, request, Produto.class, urlVariables);
    }

}
