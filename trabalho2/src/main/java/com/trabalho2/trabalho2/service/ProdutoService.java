package com.trabalho2.trabalho2.service;

import com.trabalho2.trabalho2.entities.Produto;
import java.io.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProdutoService {

    private Map<Long, Produto> produtosMapa; // Mapa associando IDs a Produtos
    private AtomicLong proximoId; // Próximo ID disponível

    private ArrayList<Produto> produtos;

    public ProdutoService() {
        produtos = new ArrayList<Produto>();
        produtosMapa = new HashMap<>();
        proximoId = new AtomicLong(1);
        if (!fileExists("produtos.dat")) {
            createFile("produtos.dat");
        }

        loadFromFile();

    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("produtos.dat"))) {
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Produto) {
                    Produto produto = (Produto) obj;
                    produtos.add(produto);
                }
            }
        } catch (EOFException e) {
            // Chegou ao final do arquivo, não é um erro
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createFile(String filename) {
        try {
            File file = new File(filename);
            file.createNewFile();
            System.out.println("Arquivo criado: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    public Produto[] getProdutos() {
        return produtos.toArray(new Produto[produtos.size()]);
    }

    public Produto addProduto(Produto produto) {
        long id = proximoId.getAndIncrement();
        produto.setId(id);
        produtosMapa.put(id, produto);
        produtos.add(produto);
        recriarMapa();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("produtos.dat"))) {
            oos.writeObject(produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return produto;
    }

    public boolean deletarProduto(long id) {
        Iterator<Produto> iterator = produtos.iterator();
        while (iterator.hasNext()) {
            Produto p = iterator.next();
            if (p.getId() == id) {
                iterator.remove();
                recriarMapa();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    private void recriarMapa() {
        Map<Long, Produto> novoMapa = new HashMap<>();
        AtomicLong novoId = new AtomicLong(1);

        for (Produto p : produtos) {
            long id = novoId.getAndIncrement();
            p.setId(id);
            novoMapa.put(id, p);
        }

        produtosMapa = novoMapa;
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("produtos.dat"))) {
            for (Produto p : produtos) {
                oos.writeObject(p);
            }
        } catch (IOException e) {
        }

    }

    public Produto updateProduto(long id, Produto produtoAtualizado) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produto.setNome(produtoAtualizado.getNome());
                produto.setPreco(produtoAtualizado.getPreco());
                saveToFile();
                return produto;
            }
        }
        return null;
    }

}
