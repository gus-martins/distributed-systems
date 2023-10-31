package questao_3;

import java.io.Serializable;

class Mensagem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String conteudo;

    public Mensagem(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }
}
