package questao_3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        final int PORTA = 9876;

        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {

            System.out.println("Servidor iniciado na porta " + PORTA);

            Controle controle = new Controle();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

                // Desempacotando a mensagem recebida sobre qual operação o cliente deseja
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Mensagem mensagem = (Mensagem) ois.readObject();

                // Empacotando a mensagem de resposta do servidor a depender da operação
                // Caso o cliente deseje Trocar um produto
                if (mensagem.getConteudo().equals("troca")) {
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    Mensagem reply = new Mensagem(
                            "Informe qual o nome do produto antigo e as informações do novo produto. (separados por vírgula)");
                    oos.writeObject(reply);

                    // Desempacotando a mensagem recebida
                    // Conteudo: tipoProduto, nomeProdutoAntigo, nomeProdutoNovo, precoProdutoNovo,
                    // autorProdutoNovo, numPaginasProdutoNovo
                    // Que serão agrupadas em um array de Strings:
                    // info1, info2, ...
                    ois = new ObjectInputStream(socket.getInputStream());
                    mensagem = (Mensagem) ois.readObject();

                    // Qual o tipo do produto?
                    String[] info = mensagem.getConteudo().split(", ");
                    String tipoProduto = info[0];

                    if (tipoProduto.equals("Livro")) {
                        // Removendo o livro antigo

                        String nomeLivroAntigo = info[1];
                        String nomeLivroNovo = info[2];
                        double precoLivroNovo = Double.parseDouble(info[3]);
                        String autorLivroNovo = info[4];
                        int numPaginasLivroNovo = Integer.parseInt(info[5]);

                        controle.trocarProdutoLivro(nomeLivroAntigo, nomeLivroNovo, precoLivroNovo, autorLivroNovo,
                                numPaginasLivroNovo);

                    } else if (tipoProduto.equals("Ebook")) {
                        // Removendo o ebook antigo

                        String nomeEbookAntigo = info[1];
                        String nomeEbookNovo = info[2];
                        double precoEbookNovo = Double.parseDouble(info[3]);
                        String autorEbookNovo = info[4];
                        double tamanhoArquivoMbEbookNovo = Double.parseDouble(info[5]);

                        controle.trocarProdutoEbook(nomeEbookAntigo, nomeEbookNovo, precoEbookNovo, autorEbookNovo,
                                tamanhoArquivoMbEbookNovo);

                    } else if (tipoProduto.equals("Apostila")) {
                        // Removendo a apostila antiga

                        String nomeApostilaAntiga = info[1];
                        String nomeApostilaNova = info[2];
                        double precoApostilaNova = Double.parseDouble(info[3]);
                        String materiaApostilaNova = info[4];
                        int numPaginasApostilaNova = Integer.parseInt(info[5]);

                        controle.trocarProdutoApostila(nomeApostilaAntiga, nomeApostilaNova, precoApostilaNova,
                                materiaApostilaNova, numPaginasApostilaNova);

                    }
                    // Caso o cliente deseje cadastrar um novo produto
                } else if (mensagem.getConteudo().equals("cadastro")) {
                    // Empacotando a mensagem de resposta do servidor
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    Mensagem reply = new Mensagem(
                            "Menu de Cadastro:");
                    oos.writeObject(reply);

                    // Desempacotando a mensagem recebida
                    ois = new ObjectInputStream(socket.getInputStream());
                    mensagem = (Mensagem) ois.readObject();

                    Produto produto = mensagem.getProduto();

                    // qual o tipo do produto
                    if (produto instanceof Livro) {
                        Livro livro = (Livro) produto;
                        controle.addLivro(livro.nome, livro.preco, livro.autor, livro.numPaginas);
                    } else if (produto instanceof Ebook) {
                        Ebook ebook = (Ebook) produto;
                        controle.addEbook(ebook.nome, ebook.preco, ebook.autor, ebook.tamanhoArquivoMb);
                    } else if (produto instanceof Apostila) {
                        Apostila apostila = (Apostila) produto;
                        controle.addApostila(apostila.nome, apostila.preco, apostila.materia, apostila.numPaginas);
                    }

                    // Empacotando a mensagem de resposta do servidor
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    reply = new Mensagem("Produto cadastrado com sucesso!");
                    oos.writeObject(reply);

                    oos.close();
                    ois.close();
                    // Caso o cliente deseje remover um produto
                } else if (mensagem.getConteudo().equals("remocao")) {
                    // Empacotando a mensagem de resposta do servidor
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    Mensagem reply = new Mensagem(
                            "Menu de Remoção:");
                    oos.writeObject(reply);

                    // Desempacotando a mensagem recebida
                    ois = new ObjectInputStream(socket.getInputStream());
                    mensagem = (Mensagem) ois.readObject();

                    // Removendo o produto
                    String[] info = mensagem.getConteudo().split(", ");
                    String tipoProduto = info[0];
                    String nomeProduto = info[1];

                    if (tipoProduto.equals("Livro")) {
                        controle.removerLivroComDeslocamento(nomeProduto);
                    } else if (tipoProduto.equals("Ebook")) {
                        controle.removerEbookComDeslocamento(nomeProduto);
                    } else if (tipoProduto.equals("Apostila")) {
                        controle.removerApostilaComDeslocamento(nomeProduto);
                    }

                    // Empacotando a mensagem de resposta do servidor
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    reply = new Mensagem("Produto removido com sucesso!");
                    oos.writeObject(reply);

                    oos.close();
                    ois.close();
                }

            }
        }

    }
}
