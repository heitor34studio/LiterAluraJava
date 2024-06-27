
![Untitled-1](https://github.com/heitor34studio/LiterAluraJava/assets/72997122/039c754f-c61b-4882-a05d-2f5f307d7479)

# LiterAlura
![Java Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![PostgreSQL Badge](https://img.shields.io/badge/PostgreSQL-232F3E?style=for-the-badge&logo=PostgreSQL&logoColor=white) ![Maven Badge](https://img.shields.io/badge/Maven-f24d38?style=for-the-badge&logo=Maven&logoColor=white) ![Spring Badge](https://img.shields.io/badge/Spring-59d141?style=for-the-badge&logo=Spring&logoColor=black) ![Jackson Badge](https://img.shields.io/badge/Jackson-FFFFFF?style=for-the-badge&logo=Jackson&logoColor=black) ![JPA Badge](https://img.shields.io/badge/JPA-9cd1d6?style=for-the-badge&logo=JPA&logoColor=black)

O projeto LiterAlura é uma ferramenta que permite ao usuário cadastrar informações sobre livros e autores(as) em seu próprio banco de dados à partir da API do [Gutendex](https://gutendex.com). 

## Índice 

* [Título e Descrição](#pesquisa-fipe)
* [Índice](#índice)
* [Funcionalidades do Projeto](#-funcionalidades-do-projeto)
* [Tecnologias Utilizadas](#%EF%B8%8F-técnicas-e-tecnologias-utilizadas)
* [Acesso ao Projeto](#-acesso-ao-projeto)
* [Abrir e Rodar o Projeto](#%EF%B8%8F-abrir-e-rodar-o-projeto)
* [Detalhamento do Código Java](#-detalhamento-do-código-java)

## 🔨 Funcionalidades do projeto

O RenSound oferece as seguintes funcionalidades:

- Cadastro de livros e autores(as) no DB.
- Listagem de todos livros cadastradas.
- Listagem de todos autores(as) cadastradas.
- Listagem de autores(as) vivos em determinado ano.
- Listagem de livros em um dos idiomas: inglês, frânces, espanhol, português.

## ✔️ Técnicas e tecnologias utilizadas

- `Java`: Linguagem principal utilizada no desenvolvimento do projeto.
- `Maven`: Ferramenta de gerenciamento de dependências e build.
- `Spring Framework`: Utilizado para facilitar a criação da aplicação.
- `JPA & PostgreSQL`: Dependências utilizadas para conexão com o DB e gerenciamento das tabelas e dos dados.

## 📁 Acesso ao projeto

Você pode acessar o código fonte do projeto [aqui](https://github.com/heitor34studio/LiterAluraJava/tree/main/src/main/java/br/com/alura/LiterAlura).

## 🛠️ Abrir e rodar o projeto

Para abrir e rodar o projeto, siga os seguintes passos:

1. Clone o repositório para o seu ambiente local:
    ```sh
    git clone https://github.com/heitor34studio/LiterAluraJava.git
    ```

2. Navegue até o diretório do projeto:
    ```sh
    cd LiterAlura; cd src; cd main; cd java; cd br; cd com; cd alura; cd LiterAlura; 
    ```

3. Compile o projeto (LiterAluraApplication.java) usando Maven:
    ```sh
    mvn clean install 
    ```

4. Execute o projeto:
    ```sh
    mvn spring-boot:run
    ```

### Detalhamento do código Java:

O código Java fornecido implementa um programa para cadastrar informações sobre livros e autores(as) em seu próprio banco de dados à partir de uma API externa.

#### main/Principal.java
Este arquivo contém a lógica principal do programa. Ele interage com o usuário com um menu e um switch-case, e chama as funções de acordo com a escolha do usuário.
As funções chamam seus respectivos scripts usando uma instância da classe LivroService, e verificam antes imprimir na tela o resultado caso o retorno seja Null ou não tenha sido de fato bem sucessido, para assim passar a mensagem correta ao usuário.

#### service/LivroService.java
Classe que contém as funções para cada opção do usuário: buscarLivroGutendex- recebe como parâmetro o nome do livro escolhido pelo usuário, e faz verificaçãos caso o livro já exista no DB antes de realizar a consulta para a API; listarLivros; listarAutores; buscarAutoresPorAno- recebe como parâmetro o ano escolhido pelo usuário; listarLivrosPorIdioma- recebe como parâmetro o idioma escolhido pelo usuário.

#### repository/AutorRepository.java e LivroRepository.java
Interfaces que extendem o Repositório JPA, e gera as funções com Querys SQL para consulta, utilizando Derived Queries do próprio JPA.

#### model/Autor.java e Livro.java
São classes de modelo para representar os(as) autores(as) e os livros que o usuário irá escolher, tendo seus próprios atributos.
Também servem como entidades para tabelas no DB que o JPA Hibernate irá criar com as diversas anotações, como: @Entity, @Table, @Id, @GeneratedValue, @Column, @OneToMany, @ManyToOne, @JoinColumn.
Atributos da classe Autor: id, nome, ano de nascimento, ano de falescimento, e uma lista de livros.
Atributos da classe Musica: id, título, idioma, e autor(a).

#### model/GutendexResponse.java
Classe de modelo para lidar com a resposta JSON do Gutendex, ignorando os primeiros valores retornados que são inúteis para o programa, e pegando o array de resultados, que traz os livros encontrados para podermos de fato extrair os livros do JSON.

#### dto/LivroDTO.java
Classe Record que é utilizada como tipo de Lista na classe GutendexResponse, para pegar os livros da resposta JSON, já que no JSON é retornada um array de strings p/ idiomas e um array de Objetos p/ os(as) autores(as), e na aplicação só iremos querer o primeiro de cada, onde o idioma será uma String e o(a) autor(a) será do tipo Autor.
Por isso precisamos do DTO.

#### service/ConsumoAPI.java; ConverteDados.java e IConverteDados.java
ConsumoAPI é uma classe que realiza requisições HTTP à API passada pelo usuário utilizando `HttpClient` e retorna os dados como strings JSON.
E a classe e Interface restantes são responsáveis por converter dados JSON em objetos Java usando a biblioteca Jackson.

### Exemplo de Uso
Ao executar o programa, o usuário pode escolher entre Cadastrar livros e autores, onde terá de informar o nome do livro para busca; Listar livros registrados; Listar autores registrados; Listar autores vivos em um determinado ano, onde terá de informar o ano escolhido; e Listar livros em um determinado idioma, onde terá de informar o idioma escolhido.


https://github.com/heitor34studio/RenSound/assets/72997122/5c00dee9-c182-4a34-af78-401add7ff801


---

Este é o README atualizado para o projeto LiterAlura. Ele fornece uma visão geral do projeto, suas funcionalidades, tecnologias utilizadas e como acessar e rodar o projeto. Para mais detalhes, você pode explorar os arquivos do código fonte mencionados.
