package com.trabalho2.trabalho2.entities;

public class Livro extends Produto {

    String autor;
    int numPaginas;

    public Livro(String nome, double preco, String autor, int numPaginas) {
        super(nome, preco);
        this.autor = autor;
        this.numPaginas = numPaginas;
    }

    @Override
    public String toString() {
        return "Livro: " + nome + " - Autor: " + autor + " - " + numPaginas + " páginas - R$"
                + String.format("%.2f", preco);
    }

    // gets e sets

    public String getAutor() {
        return autor;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

}
