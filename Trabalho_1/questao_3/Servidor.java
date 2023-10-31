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

        int length = ois.readInt();
        byte[] dados = new byte[length];
        ois.readFully(dados, 0, length);
        ByteArrayInputStream bais = new ByteArrayInputStream(dados);
        ObjectInputStream oisDesempacotado = new ObjectInputStream(bais);
        Mensagem mensagem = (Mensagem) oisDesempacotado.readObject();
        System.out.println("Mensagem recebida: " + mensagem.getConteudo());

        // Empacotando a mensagem de resposta
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oosEmpacotado = new ObjectOutputStream(baos);
        Mensagem reply = new Mensagem("Mensagem recebida no servidor. Conexão estabelecida com sucesso.");
        oosEmpacotado.writeObject(reply);
        byte[] dadosReply = baos.toByteArray();

        // Enviando a mensagem de resposta
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeInt(dadosReply.length);
        oos.write(dadosReply);
        oos.flush();

        ois.close();
        oos.close();
        socket.close();
        serverSocket.close();
    }
}
