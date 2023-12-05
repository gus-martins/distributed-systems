**RELATÓRIO - TRABALHO 2 - SISTEMAS DISTRIBUÍDOS**

Augusto César Araújo de Oliveira

Francisco Gustavo Martins de Sousa

O nosso trabalho foi implementar um sistema de biblioteca, contendo livros, apostilas e
ebooks, em que podemos cadastrar, trocar e remover objetos. Organizamos as classes em
algumas pastas:

    ● controllers: Pasta que contém as classes de controle, como Cliente.java,
    ProdutoCliente.java, ProdutoController.java e Servidor.java.
    ● entities: Pasta que contém as classes das entidades, como Apostila.java, Ebook.java,
    Livro.java e Produto.java.
    ● service: Pasta que contém o serviço implementado, contendo o arquivo ProdutoService.java.
    
**Controllers**

Nessa pasta estão organizadas as classes de controle do sistema. Em Servidor.java, há a
execução do servidor. Em ProdutoController.java, implementamos rotas para listar produtos,
adicionar um novo produto, excluir um produto com base no ID e atualizar as informações de
um produto com base no ID. Essas rotas serão usadas no arquivo ProdutoService.java. 

No arquivo Cliente.java, A aplicação solicita ao usuário que escolha entre várias opções (listar produtos, inserir produto, atualizar produto, deletar produto ou sair) e executa a operação correspondente.

**Entities**

Nessa pasta estão organizadas as entidades utilizadas, e em cada uma delas está organizado
seus atributos e métodos, como gets, sets, toString. A classe Produto.java é a interface para a criação das outras classes (Apostila.java, Ebook.java, Livro.java).

**Service** 

Nessa pasta está organizada a classe ProdutoService, responsável por implementar um serviço para a manipulação de produtos, armazenando-os em uma lista e em um arquivo. Ele fornece um CRUD, com adição, remoção e atualização de produtos, além de carregar e salvar essas informações em um arquivo.
