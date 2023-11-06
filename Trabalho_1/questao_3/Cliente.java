package questao_3;

import java.io.*;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        final int PORTA = 9876;
        final String IP = "localhost";

        // Iniciando o menu
        try (Socket socket = new Socket(IP, PORTA)) {

            do {

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

                        // Definindo o tipo de produto que o cliente deseja trocar
                        System.out.println("Qual o tipo do produto a ser trocado?");
                        System.out.println("1 - Livro");
                        System.out.println("2 - Ebook");
                        System.out.println("3 - Apostila");
                        System.out.print("Opção: ");
                        String tipoProduto = br.readLine();

                        switch (tipoProduto) {
                            case "1":
                                System.out.println("Livros:");
                                for (Produto produto : controle.getProdutos()) {
                                    if (produto instanceof Livro) {
                                        System.out.println(produto);
                                    }
                                }

                                // Empacotar a requisição de troca de produto
                                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                                Mensagem mensagem = new Mensagem("troca");
                                oos.writeObject(mensagem);

                                // Desempacotar a resposta do servidor
                                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                                Mensagem reply = (Mensagem) ois.readObject();
                                // "Menu de troca:"
                                System.out.println(reply.getConteudo());

                                // Lendo o nome do produto que o cliente deseja trocar e as informações do novo
                                // produto
                                System.out.println("Informe o nome do livro antigo: ");
                                String nomeProdutoAntigo = br.readLine();
                                System.out.println("Informe o nome do livro novo: ");
                                String nomeNovoProduto = br.readLine();
                                System.out.println("Informe o preço do livro novo: ");
                                double precoNovoProduto = Double.parseDouble(br.readLine());
                                System.out.println("Informe o autor do livro novo: ");
                                String autorNovoProduto = br.readLine();
                                System.out.println("Informe o número de páginas do livro novo: ");
                                int numPaginasNovoProduto = Integer.parseInt(br.readLine());

                                // Empacotando a mensagem
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem(tipoProduto + ", " + nomeProdutoAntigo + ", " + nomeNovoProduto
                                        + ", " + precoNovoProduto + ", " + autorNovoProduto + ", "
                                        + numPaginasNovoProduto);
                                oos.writeObject(mensagem);

                                // Desempacotando a mensagem de resposta do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                System.out.println(reply.getConteudo());

                                ois.close();
                                oos.close();
                                socket.close();
                                break;
                            case "2":
                                System.out.println("Ebooks:");
                                for (Produto produto : controle.getProdutos()) {
                                    if (produto instanceof Ebook) {
                                        System.out.println(produto);
                                    }
                                }

                                // Empacotar a requisição de troca de produto
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem("troca");
                                oos.writeObject(mensagem);

                                // Desempacotar a resposta do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                // "Menu de troca:"
                                System.out.println(reply.getConteudo());

                                // Lendo o nome do produto que o cliente deseja trocar e as informações do novo
                                // produto
                                System.out.println("Informe o nome do ebook antigo: ");
                                nomeProdutoAntigo = br.readLine();
                                System.out.println("Informe o nome do ebook novo: ");
                                nomeNovoProduto = br.readLine();
                                System.out.println("Informe o preço do ebook novo: ");
                                precoNovoProduto = Double.parseDouble(br.readLine());
                                System.out.println("Informe o autor do ebook novo: ");
                                autorNovoProduto = br.readLine();
                                System.out.println("Informe o tamanho do arquivo do ebook novo: ");
                                double tamanhoArquivoMb = Double.parseDouble(br.readLine());

                                // Empacotando a mensagem
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem(tipoProduto + ", " + nomeProdutoAntigo + ", " + nomeNovoProduto
                                        + ", " + precoNovoProduto + ", " + autorNovoProduto + ", " + tamanhoArquivoMb);
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
                                System.out.println("Apostilas:");
                                for (Produto produto : controle.getProdutos()) {
                                    if (produto instanceof Apostila) {
                                        System.out.println(produto);
                                    }
                                }

                                // Empacotar a requisição de troca de produto
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem("troca");
                                oos.writeObject(mensagem);

                                // Desempacotar a resposta do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                // "Menu de troca:"
                                System.out.println(reply.getConteudo());

                                // Lendo o nome do produto que o cliente deseja trocar e as informações do novo
                                // produto
                                System.out.println("Informe o nome da apostila antiga: ");
                                nomeProdutoAntigo = br.readLine();
                                System.out.println("Informe o nome da apostila nova: ");
                                nomeNovoProduto = br.readLine();
                                System.out.println("Informe o preço da apostila nova: ");
                                precoNovoProduto = Double.parseDouble(br.readLine());
                                System.out.println("Informe a matéria da apostila nova: ");
                                String materia = br.readLine();
                                System.out.println("Informe o número de páginas da apostila nova: ");
                                numPaginasNovoProduto = Integer.parseInt(br.readLine());

                                // Empacotando a mensagem
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem(tipoProduto + ", " + nomeProdutoAntigo + ", " + nomeNovoProduto
                                        + ", " + precoNovoProduto + ", " + materia + ", " + numPaginasNovoProduto);
                                oos.writeObject(mensagem);

                                // Desempacotando a mensagem de resposta do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                System.out.println(reply.getConteudo());

                                ois.close();
                                oos.close();
                                socket.close();
                                break;
                            default:
                                break;
                        }
                    case "3":
                        // Definindo o tipo de produto que o cliente deseja cadastrar
                        System.out.println("Qual o tipo do produto a ser cadastrado?");
                        System.out.println("1 - Livro");
                        System.out.println("2 - Ebook");
                        System.out.println("3 - Apostila");
                        System.out.print("Opção: ");
                        tipoProduto = br.readLine();

                        switch (tipoProduto) {
                            case "1":
                                // Livro
                                // Empacotar a requisição de cadastro de produto
                                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                                Mensagem mensagem = new Mensagem("cadastro");
                                oos.writeObject(mensagem);

                                // Desempacotar a resposta do servidor
                                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                                Mensagem reply = (Mensagem) ois.readObject();
                                // "Informe o nome, o preço, o autor e o número de páginas do livro (separados
                                // por vírgula)"
                                System.out.println(reply.getConteudo());

                                // Lendo o nome do produto que o cliente deseja trocar e as informações do novo
                                // produto
                                System.out.println("Informe o nome do livro: ");
                                String nomeProduto = br.readLine();
                                System.out.println("Informe o preço do livro: ");
                                double precoProduto = Double.parseDouble(br.readLine());
                                System.out.println("Informe o autor do livro: ");
                                String autorProduto = br.readLine();
                                System.out.println("Informe o número de páginas do livro: ");
                                int numPaginasProduto = Integer.parseInt(br.readLine());

                                // Transformando a mensagem em um objeto Produto
                                Produto produto = new Livro(nomeProduto, precoProduto, autorProduto, numPaginasProduto);

                                // Empacotando a mensagem
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem(produto);
                                oos.writeObject(mensagem);

                                // Desempacotando a mensagem de resposta do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                System.out.println(reply.getConteudo());

                                ois.close();
                                oos.close();
                                break;
                            case "2":
                                // Ebook
                                // Empacotar a requisição de cadastro de produto
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem("cadastro");
                                oos.writeObject(mensagem);

                                // Desempacotar a resposta do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                // "Informe o nome, o preço, o autor e o tamanho do arquivo do ebook (separados
                                // por vírgula)"
                                System.out.println(reply.getConteudo());

                                // Lendo o nome do produto que o cliente deseja trocar e as informações do novo
                                // produto
                                System.out.println("Informe o nome do ebook: ");
                                nomeProduto = br.readLine();
                                System.out.println("Informe o preço do ebook: ");
                                precoProduto = Double.parseDouble(br.readLine());
                                System.out.println("Informe o autor do ebook: ");
                                autorProduto = br.readLine();
                                System.out.println("Informe o tamanho do arquivo do ebook: ");
                                double tamanhoArquivoMb = Double.parseDouble(br.readLine());

                                // Transformando a mensagem em um objeto Produto
                                produto = new Ebook(nomeProduto, precoProduto, autorProduto, tamanhoArquivoMb);

                                // Empacotando a mensagem
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem(produto);
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
                                // Apostila
                                // Empacotar a requisição de cadastro de produto
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem("cadastro");
                                oos.writeObject(mensagem);

                                // Desempacotar a resposta do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                // "Informe o nome, o preço, o autor e o número de páginas da apostila
                                // (separados por vírgula)"
                                System.out.println(reply.getConteudo());

                                // Lendo o nome do produto que o cliente deseja trocar e as informações do novo
                                // produto
                                System.out.println("Informe o nome da apostila: ");
                                nomeProduto = br.readLine();
                                System.out.println("Informe o preço da apostila: ");
                                precoProduto = Double.parseDouble(br.readLine());
                                System.out.println("Informe o autor da apostila: ");
                                autorProduto = br.readLine();
                                System.out.println("Informe o número de páginas da apostila: ");
                                numPaginasProduto = Integer.parseInt(br.readLine());

                                // Transformando a mensagem em um objeto Produto
                                produto = new Apostila(nomeProduto, precoProduto, autorProduto, numPaginasProduto);

                                // Empacotando a mensagem
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem(produto);
                                oos.writeObject(mensagem);

                                // Desempacotando a mensagem de resposta do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                System.out.println(reply.getConteudo());

                                ois.close();
                                oos.close();
                                break;
                        }
                        break;
                    case "4":
                        // Definindo o tipo de produto que o cliente deseja remover
                        System.out.println("Qual o tipo do produto a ser removido?");
                        System.out.println("1 - Livro");
                        System.out.println("2 - Ebook");
                        System.out.println("3 - Apostila");
                        System.out.print("Opção: ");
                        tipoProduto = br.readLine();

                        switch (tipoProduto) {
                            case "1":
                                // Empacotar a requisição de remoção de produto
                                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                                Mensagem mensagem = new Mensagem("remocao");
                                oos.writeObject(mensagem);

                                // Desempacotar a resposta do servidor
                                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                                Mensagem reply = (Mensagem) ois.readObject();
                                // "Menu de remoção"
                                System.out.println(reply.getConteudo());

                                System.out.println("Qual o nome do produto que o cliente deseja remover?");
                                // Lendo o nome do produto que o cliente deseja remover
                                String nomeProduto = br.readLine();

                                // Empacotando a mensagem
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem(tipoProduto + ", " + nomeProduto);
                                oos.writeObject(mensagem);

                                // Desempacotando a mensagem de resposta do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                System.out.println(reply.getConteudo());

                                ois.close();
                                oos.close();
                                socket.close();
                                break;
                            case "2":
                                System.out.println("Ebooks:");
                                for (Produto ebook : controle.getProdutos()) {
                                    if (ebook instanceof Ebook) {
                                        System.out.println(ebook);
                                    }
                                }

                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem("remocao");
                                oos.writeObject(mensagem);

                                // Desempacotar a resposta do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                // "Menu de remoção"
                                System.out.println(reply.getConteudo());

                                System.out.println("Qual o nome do produto que o cliente deseja remover?");

                                // Lendo o nome do produto que o cliente deseja remover
                                nomeProduto = br.readLine();

                                // Empacotando a mensagem
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem(tipoProduto + ", " + nomeProduto);
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
                                System.out.println("Apostilas:");
                                for (Produto apostila : controle.getProdutos()) {
                                    if (apostila instanceof Apostila) {
                                        System.out.println(apostila);
                                    }
                                }

                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem("remocao");
                                oos.writeObject(mensagem);

                                // Desempacotar a resposta do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                // "Menu de remoção"
                                System.out.println(reply.getConteudo());

                                System.out.println("Qual o nome do produto que o cliente deseja remover?");

                                // Lendo o nome do produto que o cliente deseja remover
                                nomeProduto = br.readLine();

                                // Empacotando a mensagem
                                oos = new ObjectOutputStream(socket.getOutputStream());
                                mensagem = new Mensagem(tipoProduto + ", " + nomeProduto);
                                oos.writeObject(mensagem);

                                // Desempacotando a mensagem de resposta de sucesso do servidor
                                ois = new ObjectInputStream(socket.getInputStream());
                                reply = (Mensagem) ois.readObject();
                                System.out.println(reply.getConteudo());

                                ois.close();
                                oos.close();
                                socket.close();
                                break;
                            default:
                                break;
                        }
                        break;
                    case "5":
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
                socket.close();
                break;
            } while (true);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Throwable e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            System.out.println("Fim do programa");
        }
    }
}
