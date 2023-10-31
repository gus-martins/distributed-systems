package questao_3;

public class Produto {

    String nome;
    double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " - R$" + String.format("%.2f", preco);
    }

    public static void main(String[] args) {

    }

}

class Livro extends Produto {
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
}

class Ebook extends Produto {
    String autor;
    double tamanhoArquivoMb;

    public Ebook(String nome, double preco, String autor, double tamanhoArquivoMb) {
        super(nome, preco);
        this.autor = autor;
        this.tamanhoArquivoMb = tamanhoArquivoMb;
    }

    @Override
    public String toString() {
        return "E-book: " + nome + " - Autor: " + autor + " - Tamanho do arquivo: " + tamanhoArquivoMb + " MB - R$"
                + String.format("%.2f", preco);
    }
}

class Apostila extends Produto {
    String materia;
    int numPaginas;

    public Apostila(String nome, double preco, String materia, int numPaginas) {
        super(nome, preco);
        this.materia = materia;
        this.numPaginas = numPaginas;
    }

    @Override
    public String toString() {
        return "Apostila: " + nome + " - Matéria: " + materia + " - " + numPaginas + " páginas - R$"
                + String.format("%.2f", preco);
    }
}
