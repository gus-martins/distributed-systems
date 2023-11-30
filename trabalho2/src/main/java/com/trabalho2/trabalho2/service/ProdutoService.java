package com.trabalho2.trabalho2.service;

import com.trabalho2.trabalho2.entities.Produto;
import java.io.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ProdutoService {

    private ArrayList<Produto> produtos;

    public ProdutoService() {
        produtos = new ArrayList<Produto>();
        if (!fileExists("produtos.dat")) {
            createFile("produtos.dat");
        }
        loadFromFile();
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("produtos.dat"))) {
            while (true) {
                Produto produto = (Produto) ois.readObject();
                produtos.add(produto);
            }
        } catch (EOFException e) {
            e.getMessage();

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

    public Produto getProdutoById(int id) {
        for (Produto p : produtos) {
            if (p.getSerialversionuid() == id) {
                return p;
            }
        }
        return null;
    }

    public Produto addProduto(Produto produto) {
        produtos.add(produto);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("produtos.dat"))) {
            oos.writeObject(produto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return produto;
    }

    public boolean deletarProduto(long id) {
        for (Produto p : produtos) {
            if (p.getSerialversionuid() == id) {
                produtos.remove(p);
                listarProdutos();
                return true;
            }
        }
        return false;
    }

    private void listarProdutos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("produtos.dat"))) {
            for (Produto p : produtos) {
                oos.writeObject(p);
            }
        } catch (IOException e) {
        }

    }

    public Produto updateProduto(String nomeAntigo, Produto produto) {
        for (Produto p : produtos) {
            if (p.getNome().equals(nomeAntigo)) {
                produtos.remove(p);
                produtos.add(produto);
                listarProdutos();
                return produto;
            }
        }
        return null;
    }

}
