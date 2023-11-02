package questao_3;

import java.io.*;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Iniciando o menu
        try {

            while (true) {

                Socket socket = new Socket("localhost", 9876); // IP e porta do servidor
                Controle controle = new Controle();

                System.out.println("Escolha uma opção:");
                System.out.println("1 - Listar produtos");
                System.out.println("2 - Trocar produto");
                System.out.println("3 - Sair");
                System.out.print("Opção: ");

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String opcao = br.readLine();

                switch (opcao) {
                    case "1":
                        System.out.println("Produtos:");
                        for (Produto produto : controle.getProdutos()) {
                            System.out.println(produto);
                        }
                        break;
                    case "2":
                        System.out.println("Digite o nome do produto que deseja trocar:");
                        String nome = br.readLine();
                        // Empacotando a mensagem

                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        Mensagem mensagem = new Mensagem(nome);
                        oos.writeObject(mensagem);

                        // Desempacotando a mensagem de resposta do servidor
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        Mensagem reply = (Mensagem) ois.readObject();
                        System.out.println(reply.getConteudo());

                        ois.close();
                        oos.close();
                        socket.close();
                        break;
                    case "3":
                        System.out.println("Saindo...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
                socket.close();
                break;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Throwable e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
