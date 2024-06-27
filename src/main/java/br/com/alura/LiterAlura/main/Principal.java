package br.com.alura.LiterAlura.main;

import br.com.alura.LiterAlura.model.Autor;
import br.com.alura.LiterAlura.model.Livro;
import br.com.alura.LiterAlura.service.LivroService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final LivroService service;

    public Principal(LivroService service) {
        this.service = service;
    }

    public void exibeLivros(List<Livro> listaLivros, String mensagem) {
        if (listaLivros.isEmpty()) {
            System.out.println(mensagem);
        } else {
            listaLivros.forEach(livro -> System.out.printf("""
                        
                        ----- LIVRO -----
                        Título: %s
                        Autor: %s
                        Idioma: %s
                        Número de Downloads: %f.1
                        -----------------
                        
                        """,
                    livro.getTitulo(),
                    livro.getAutor().getNome(),
                    livro.getIdioma(),
                    livro.getDownloads()));
        }
    }

    public void exibeAutores(List<Autor> listaAutores, String mensagem) {
        if (listaAutores.isEmpty()) {
            System.out.println(mensagem);
        } else {
            listaAutores.forEach(autor -> {
                List<String> nomesLivros = new ArrayList<>();
                autor.getLivros().forEach(n -> nomesLivros.add(n.getTitulo()));
                System.out.printf("""
                        
                        ----- AUTOR -----
                        Nome: %s
                        Ano de Nascimento: %s
                        Ano de Falescimento: %s
                        Livros: %s
                        -----------------
                        
                        """,
                        autor.getNome(),
                        autor.getAnoNascimento(),
                        autor.getAnoFalescimento(),
                        nomesLivros);
            });
        }
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """ 
                    *** LiterAlura de Heitor Renthon 34 ***
                    
                    1 - Buscar e cadastrar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void listarLivrosPorIdioma() {
        String mensagem = "Não há livros nesse idioma no banco de dados";
        System.out.println("""
                Escolha o idioma para realizar a busca:
                es- Espanhol
                en- Inglês
                fr- Francês
                pt- Português
                """);
        var idioma = leitura.nextLine();
        List<Livro> listaLivros = service.listarLivrosPorIdioma(idioma);
        exibeLivros(listaLivros, mensagem);
    }

    private void listarAutoresPorAno() {
        String mensagem = "Ninguém que já foi registrado no banco de dados estava vivo neste ano...";
        System.out.println("Insira o que deseja buscar:");
        var ano = leitura.nextInt();
        leitura.nextLine();
        List<Autor> listaAutores = service.buscaAutoresPorAno(ano);
        exibeAutores(listaAutores, mensagem);
    }

    private void listarAutores() {
        String mensagem = "Não há ninguém salvo no banco de dados... Utilize a opção 1 para " +
                "registrar um livro e seu autor junto!";
        List<Autor> listaAutores = service.listarAutores();
        exibeAutores(listaAutores, mensagem);
    }

    private void listarLivros() {
        String mensagem = "Não há livros salvos no banco de dados... Utilize a opção 1 para " +
                "registrar um livro e seu autor junto!";
        List<Livro> listaLivros = service.listarLivros();
        exibeLivros(listaLivros, mensagem);
    }

    private void buscarLivro() {
        System.out.println("Insira o nome do livro a ser buscado:");
        var nomeLivro = leitura.nextLine();
        Livro livro = service.buscarLivroGutendex(nomeLivro);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
        } else {
            System.out.printf("""
                        
                        ----- LIVRO -----
                        Título: %s
                        Autor: %s
                        Idioma: %s
                        Número de Downloads: %f.2
                        -----------------
                        
                        """,
                    livro.getTitulo(),
                    livro.getAutor().getNome(),
                    livro.getIdioma(),
                    livro.getDownloads());
        }
    }

//    private void buscarMusicaPorArtista() {
//        System.out.println("Insira o nome do(a) Artista dono(a) das músicas:");
//        var nomeArtista = leitura.nextLine();
//        List<Musica> musicas = repositorio.encontrarMusicasPorArtista(nomeArtista);
//        musicas.forEach(m -> System.out.println(m.getArtista().getNome() + "- " +m.getTitulo()));
//    }
//
//    private void listarMusicas() {
//        List<Artista> artistasLista = repositorio.findAll();
//        artistasLista.forEach(a -> a.getMusicas().forEach(m -> System.out.println(m.getArtista().getNome()
//                + "- "+ m.getTitulo())));
//    }
//
//    private void cadastrarMusicas() {
//        List<Musica> musicasLista = new ArrayList<>();
//        System.out.println("Insira o nome do(a) Artista dono(a) da música:");
//        var nomeArtista = leitura.nextLine();
//        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nomeArtista);
//        if (artista.isPresent()) {
//            Artista artistaEncontrado = artista.get();
//            var opcaoContinuar = "S";
//            while (opcaoContinuar.equalsIgnoreCase("s")) {
//                System.out.println("Insira o nome da música do(a) artista " +
//                        artistaEncontrado.getNome() + ":");
//                var musicaDoArtista = leitura.nextLine();
//                Musica musica = new Musica(musicaDoArtista, artistaEncontrado);
//                musicasLista.add(musica);
//                System.out.println("Quer cadastrar outra música? (S/N)");
//                opcaoContinuar = leitura.nextLine();
//            }
//            artistaEncontrado.setMusicas(musicasLista);
//            repositorio.save(artistaEncontrado);
//        } else {
//            System.out.println("Artista não encontrado(a). Cadastre ele primeiro com a opção 1.");
//        }
//    }
//
//    private void cadastrarArtistas() {
//        var opcaoContinuar = "S";
//        while (opcaoContinuar.equalsIgnoreCase("s")) {
//            System.out.println("Insira o nome do(a) Artista:");
//            var nomeArtista = leitura.nextLine();
//            System.out.println("Informe o tipo desse artista: (solo, dupla, ou banda)");
//            var tipoArtista = leitura.nextLine();
//            Artista artista = new Artista(nomeArtista, tipoArtista);
//            repositorio.save(artista);
//            System.out.println("Quer cadastrar outro artista? (S/N)");
//            opcaoContinuar = leitura.nextLine();
//        }
//    }
}
