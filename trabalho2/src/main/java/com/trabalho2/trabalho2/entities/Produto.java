package com.trabalho2.trabalho2.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Produto implements Serializable {

    private String nome;
    private double preco;
    private long id;

    public Produto() {
        this.nome = "";
        this.preco = 0;
    }

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - R$" + String.format("%.2f", preco);
    }

    public static void main(String[] args) {

    }

    // gets e sets

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
