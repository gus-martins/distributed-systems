package questao_3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Iniciando o servidor
        System.out.println("Servidor iniciado");
        ServerSocket serverSocket = new ServerSocket(9876); // Porta do servidor

        // Aguardando conexão do cliente
        System.out.println("Aguardando conexão do cliente...");
        Socket socket = serverSocket.accept();
        System.out.println(socket.getInetAddress());
        System.out.println("Conexão estabelecida");

        // Desempacotando a mensagem recebida
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Mensagem mensagem = (Mensagem) ois.readObject();

        // Empacotando a mensagem de resposta do servidor
        if (mensagem.getConteudo().equals("troca")) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Mensagem reply = new Mensagem(
                    "Qual o nome do produto que o cliente deseja trocar? e as informações do novo produto?");
            oos.writeObject(reply);

            // Desempacotando a mensagem recebida
            ois = new ObjectInputStream(socket.getInputStream());
            mensagem = (Mensagem) ois.readObject();

            // Lendo o nome do produto que o cliente deseja trocar e as informações do novo
            // produto
            String[] info = mensagem.getConteudo().split(",");
            String nomeProduto = info[0];
            String nomeNovoProduto = info[1];
            double precoNovoProduto = Double.parseDouble(info[2]);
            String autorNovoProduto = info[3];
            int numPaginasNovoProduto = Integer.parseInt(info[4]);

            // Trocando o produto
            Controle controle = new Controle();
            controle.trocarProdutoLivro(nomeProduto, nomeNovoProduto, precoNovoProduto, autorNovoProduto,
                    numPaginasNovoProduto);

        }

        Controle controle = new Controle();

        ois.close();
        socket.close();
        serverSocket.close();
    }

}
