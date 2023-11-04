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
                System.out.println("3 - Cadastrar produto");
                System.out.println("4 - Remover produto");
                System.out.println("5 - Sair");
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

                        // Empacotar a requisição de troca de produto
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        Mensagem mensagem = new Mensagem("troca");
                        oos.writeObject(mensagem);

                        // Desempacotar a resposta do servidor
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        Mensagem reply = (Mensagem) ois.readObject();
                        // "Qual o nome do produto que o cliente deseja trocar? e as informações do novo
                        // produto?"
                        // OBS: divida as informações por vírgula
                        System.out.println(reply.getConteudo());

                        // Lendo o nome do produto que o cliente deseja trocar e as informações do novo
                        // produto
                        String[] info = br.readLine().split(",");
                        String nomeProduto = info[0];
                        String nomeNovoProduto = info[1];
                        double precoNovoProduto = Double.parseDouble(info[2]);
                        String autorNovoProduto = info[3];
                        int numPaginasNovoProduto = Integer.parseInt(info[4]);

                        // Empacotando a mensagem
                        oos = new ObjectOutputStream(socket.getOutputStream());
                        mensagem = new Mensagem(nomeProduto + "," + nomeNovoProduto + "," +
                                precoNovoProduto + "," + autorNovoProduto + "," + numPaginasNovoProduto);
                        oos.writeObject(mensagem);

                        // Desempacotando a mensagem de resposta do servidor
                        ois = new ObjectInputStream(socket.getInputStream());
                        reply = (Mensagem) ois.readObject();
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
