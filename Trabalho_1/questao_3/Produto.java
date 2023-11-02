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

class Controle {

    private Produto[] produtos;

    public Controle() {

        produtos = new Produto[10];

        produtos[0] = new Livro("Guerra e Paz", 59.90, "Liev Tolstói", 1000);
        produtos[1] = new Livro("Guerra dos Tronos", 39.90, "George R. R. Martin", 500);
        produtos[2] = new Ebook("O Silmarillion", 29.90, "J. R. R. Tolkien", 5.0);
        produtos[3] = new Ebook("O Guia do Mochileiro das Galáxias", 19.90, "Douglas Adams", 2.0);
        produtos[4] = new Apostila("Apostila de Java", 9.90, "Java", 100);
        produtos[5] = new Apostila("Apostila de Python", 9.90, "Python", 100);
        produtos[6] = new Livro("O Elefante Desaparece", 29.90, "Haruki Murakami", 300);
        produtos[7] = new Livro("172 Horas na Lua", 19.90, "Johan Harstad", 200);
        produtos[8] = new Ebook("172 Horas na Lua", 19.90, "Johan Harstad", 0.5);
        produtos[9] = new Ebook("O Elefante Desaparece", 19.90, "Haruki Murakami", 0.5);

    }

    public Produto[] getProdutos() {
        return produtos;
    }

    public boolean trocarProduto(String nomeProduto) {
        for (Produto produto : produtos) {
            if (produto.nome.equals(nomeProduto)) {
                return true;
            }
        }
        return false;
    }

}
