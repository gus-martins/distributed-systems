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
        // comparando o nome do produto recebido com os produtos do servidor
        // respondendo "troca efetuada" ou "produto não encontrado", baseado no nome

        Controle controle = new Controle();
        String nome = mensagem.getConteudo();

        if (controle.trocarProduto(nome)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            mensagem.setConteudo("Troca efetuada");
            oos.writeObject(mensagem);
            oos.close();
        } else {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            mensagem.setConteudo("Produto não encontrado");
            oos.writeObject(mensagem);
            oos.close();
        }

        ois.close();
        socket.close();
        serverSocket.close();
    }

}
