package com.trabalho2.trabalho2.entities;

public class Ebook extends Produto {

    String autor;
    double numPaginas;

    public Ebook(String nome, double preco, String autor, int numPaginas) {
        super(nome, preco);
        this.autor = autor;
        this.numPaginas = numPaginas;
    }

    @Override
    public String toString() {
        return "E-book: " + nome + " - Autor: " + autor + " - " + numPaginas + " páginas - R$"
                + String.format("%.2f", preco);
    }

    // gets e sets

    public String getAutor() {
        return autor;
    }

    public double getNumPaginas() {
        return numPaginas;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setNumPaginas(double numPaginas) {
        this.numPaginas = numPaginas;
    }

}
