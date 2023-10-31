package questao_3;

import java.io.*;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Iniciando o cliente
        System.out.println("Cliente iniciado");
        Socket socket = new Socket("localhost", 9876); // IP e porta do servidor

        // Empacotando a mensagem
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        Mensagem mensagem = new Mensagem("Mensagem enviada do cliente para o servidor.");
        oos.writeObject(mensagem);
        byte[] dados = baos.toByteArray();

        // Enviando a mensagem empacotada para o servidor
        oos.writeInt(dados.length);
        oos.write(dados);
        oos.flush();

        // Desempacotando a mensagem de resposta do servidor
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        int length = ois.readInt();
        byte[] dadosReply = new byte[length];
        ois.readFully(dadosReply, 0, length);
        ByteArrayInputStream bais = new ByteArrayInputStream(dadosReply);
        ObjectInputStream oisDesempacotado = new ObjectInputStream(bais);
        Mensagem reply = (Mensagem) oisDesempacotado.readObject();
        System.out.println("Mensagem recebida do servidor: " + reply.getConteudo());

        ois.close();
        oos.close();
        socket.close();
    }
}
