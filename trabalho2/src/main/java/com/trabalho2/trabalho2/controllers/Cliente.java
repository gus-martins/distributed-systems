package com.trabalho2.trabalho2.controllers;

import com.trabalho2.trabalho2.entities.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Cliente {

    public static void main(String[] args) throws IOException {
        ProdutoCliente produtoCliente = new ProdutoCliente();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcao;

        while (true) {
            System.out.println("Digite a opção desejada: ");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Inserir produto");
            System.out.println("3 - Atualizar produto");
            System.out.println("4 - Deletar produto");
            System.out.println("5 - Sair");
            opcao = reader.readLine();
            switch (opcao) {
                case "1":
                    ResponseEntity<Produto[]> response = produtoCliente.getProdutos();
                    if (response.getStatusCode() == HttpStatus.OK) {
                        Produto[] produtos = response.getBody();
                        for (Produto p : produtos) {
                            System.out.println(p.toString());
                        }
                    } else {
                        System.out.println("Erro ao buscar os produtos. Status: " + response.getStatusCodeValue());
                    }

                    break;
                case "2":
                    Produto produto = dadosProduto(reader);
                    produtoCliente.createProduto(produto);
                    break;
                case "3":
                    System.out.println("Digite o id do produto: ");
                    int id = Integer.parseInt(reader.readLine());
                    produto = dadosProduto(reader);
                    produtoCliente.updateProduto(id, produto);
                    break;
                case "4":
                    System.out.println("Digite o id do produto: ");
                    id = Integer.parseInt(reader.readLine());
                    produtoCliente.deleteProduto(id);
                    break;
                case "5":
                    System.out.println("Aplicação Encerrada");
                    return;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

        }
    }

    public static Produto dadosProduto(BufferedReader reader) throws IOException {
        Produto produto = new Produto();
        System.out.println("Digite o nome do produto: ");
        produto.setNome(reader.readLine());
        System.out.println("Digite o preço do produto: ");
        produto.setPreco(Double.parseDouble(reader.readLine()));

        return produto;

    }
}
