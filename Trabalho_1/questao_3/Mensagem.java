package questao_3;

import java.io.Serializable;

class Mensagem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String conteudo;
    private Produto produto;

    public Mensagem(String conteudo) {
        this.conteudo = conteudo;
    }

    public Mensagem(Produto produto) {
        this.produto = produto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setConteudo(String mensagem) {
        this.conteudo = mensagem;
    }
}
