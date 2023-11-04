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
    }

    public void incriseSize() {
        Produto[] produtosTemp = new Produto[produtos.length + 10];
        for (int i = 0; i < produtos.length; i++) {
            produtosTemp[i] = produtos[i];
        }
        produtos = produtosTemp;
    }

    public boolean isEmpty() {
        return produtos[0] == null;
    }

    public boolean isFull() {
        return produtos[produtos.length - 1] != null;
    }

    public Produto[] getProdutos() {
        if (isEmpty()) {
            System.out.println("Não há produtos cadastrados");
            return null;
        }
        return produtos;
    }

    public void addLivro(String nome, double preco, String autor, int numPaginas) {
        if (isEmpty()) {
            produtos[0] = new Livro(nome, preco, autor, numPaginas);
        } else if (isFull()) {
            incriseSize();
            for (int i = 0; i < produtos.length; i++) {
                if (produtos[i] == null) {
                    produtos[i] = new Livro(nome, preco, autor, numPaginas);
                    break;
                }
            }
        } else {
            for (int i = 0; i < produtos.length; i++) {
                if (produtos[i] == null) {
                    produtos[i] = new Livro(nome, preco, autor, numPaginas);
                    break;
                }
            }
        }
    }

    public void addEbook(String nome, double preco, String autor, double tamanhoArquivoMb) {
        if (isEmpty()) {
            produtos[0] = new Ebook(nome, preco, autor, tamanhoArquivoMb);
        } else if (isFull()) {
            incriseSize();
            for (int i = 0; i < produtos.length; i++) {
                if (produtos[i] == null) {
                    produtos[i] = new Ebook(nome, preco, autor, tamanhoArquivoMb);
                    break;
                }
            }
        } else {
            for (int i = 0; i < produtos.length; i++) {
                if (produtos[i] == null) {
                    produtos[i] = new Ebook(nome, preco, autor, tamanhoArquivoMb);
                    break;
                }
            }
        }
    }

    public void addApostila(String nome, double preco, String materia, int numPaginas) {
        if (isEmpty()) {
            produtos[0] = new Apostila(nome, preco, materia, numPaginas);
        } else if (isFull()) {
            incriseSize();
            for (int i = 0; i < produtos.length; i++) {
                if (produtos[i] == null) {
                    produtos[i] = new Apostila(nome, preco, materia, numPaginas);
                    break;
                }
            }
        } else {
            for (int i = 0; i < produtos.length; i++) {
                if (produtos[i] == null) {
                    produtos[i] = new Apostila(nome, preco, materia, numPaginas);
                    break;
                }
            }
        }
    }

    public void removeProdutoComDeslocamento(String nome) {
        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i].nome.equals(nome)) {
                produtos[i] = null;
                for (int j = i; j < produtos.length - 1; j++) {
                    produtos[j] = produtos[j + 1];
                }
                produtos[produtos.length - 1] = null;
                break;
            }
        }
    }

    public void trocarProdutoLivro(String nomeAntigo, String nomeNovo, double preco, String autor, int numPaginas) {
        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i].nome.equals(nomeAntigo)) {
                produtos[i] = new Livro(nomeNovo, preco, autor, numPaginas);
                break;
            } else if (produtos[i] == null) {
                System.out.println("Produto não encontrado");
                break;
            }
        }
    }

    public void trocarProdutoEbook(String nomeAntigo, String nomeNovo, double preco, String autor,
            double tamanhoArquivoMb) {
        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i].nome.equals(nomeAntigo)) {
                produtos[i] = new Ebook(nomeNovo, preco, autor, tamanhoArquivoMb);
                break;
            } else if (produtos[i] == null) {
                System.out.println("Produto não encontrado");
                break;
            }
        }
    }

    public void trocarProdutoApostila(String nomeAntigo, String nomeNovo, double preco, String materia,
            int numPaginas) {
        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i].nome.equals(nomeAntigo)) {
                produtos[i] = new Apostila(nomeNovo, preco, materia, numPaginas);
                break;
            } else if (produtos[i] == null) {
                System.out.println("Produto não encontrado");
                break;
            }
        }
    }

}
