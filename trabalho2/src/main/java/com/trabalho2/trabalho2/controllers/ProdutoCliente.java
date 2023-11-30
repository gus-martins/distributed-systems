package com.trabalho2.trabalho2.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.trabalho2.trabalho2.entities.Produto;

public class ProdutoCliente {

    private final String URL = "http://localhost:1234";
    private final RestTemplate restTemplate;

    public ProdutoCliente() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<Produto> getProdutoById(int id) {
        String url = URL + "/produtos/" + id;
        return restTemplate.getForEntity(url, Produto.class);
    }

    public ResponseEntity<Produto> createProduto(Produto produto) {
        String url = URL + "/produtos";
        return restTemplate.postForEntity(url, produto, Produto.class);
    }

    public ResponseEntity<Void> deleteProduto(int id) {
        String url = URL + "/produtos/" + id;
        restTemplate.delete(url);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Produto> updateProduto(int id, Produto produto) {
        String url = URL + "/produtos/" + id;
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(produto), Produto.class);
    }

}
