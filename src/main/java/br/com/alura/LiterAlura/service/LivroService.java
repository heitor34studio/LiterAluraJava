package br.com.alura.LiterAlura.service;

import br.com.alura.LiterAlura.dto.LivroDTO;
import br.com.alura.LiterAlura.model.Autor;
import br.com.alura.LiterAlura.model.GutendexResponse;
import br.com.alura.LiterAlura.model.Livro;
import br.com.alura.LiterAlura.repository.AutorRepository;
import br.com.alura.LiterAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepositorio;
    @Autowired
    private AutorRepository autorRepositorio;
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String URL = "https://gutendex.com/books/";

    public Livro buscarLivroGutendex(String nomeDoLivro) {
        try {
            Optional<Livro> livroCasoExista = livroRepositorio.findByTitulo(nomeDoLivro);
            if(livroCasoExista.isPresent()){
                return livroCasoExista.get();
            }
            var encodedNome = URLEncoder.encode(nomeDoLivro, StandardCharsets.UTF_8.toString());
            var urlAtualizada = URL + "?search=" + encodedNome;
            String json = consumoApi.obterDados(urlAtualizada);
            GutendexResponse respostaGutendex = conversor.obterDados(json, GutendexResponse.class);
            LivroDTO dadosLivroRetornado = respostaGutendex.getResults().get(0);
            Livro primeiroLivroRetornado = new Livro(dadosLivroRetornado.titulo(),
                    dadosLivroRetornado.idioma().get(0),
                    dadosLivroRetornado.downloads(),
                    dadosLivroRetornado.autor().get(0));
            Optional<Autor> autor = autorRepositorio.findByNome(primeiroLivroRetornado.getAutor().getNome());
            if (autor.isPresent()) {
                Autor autorEncontrado = autor.get();
                primeiroLivroRetornado.setAutor(autorEncontrado);
                autorEncontrado.getLivros().add(primeiroLivroRetornado);
                autorRepositorio.save(autorEncontrado);
                return primeiroLivroRetornado;
            }
            Autor autorDoLivroRetornado = primeiroLivroRetornado.getAutor();
            List<Livro> livrosLista = new ArrayList<>();
            livrosLista.add(primeiroLivroRetornado);
            autorDoLivroRetornado.setLivros(livrosLista);
            autorRepositorio.save(autorDoLivroRetornado);
            return primeiroLivroRetornado;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Erro ao codificar texto inserido para a Url.");
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public List<Livro> listarLivros() {
        return livroRepositorio.findAll();
    }

    public List<Autor> listarAutores() {
        return autorRepositorio.findAll();
    }

    public List<Autor> buscaAutoresPorAno(int ano) {
        return autorRepositorio.
                findByAnoNascimentoLessThanEqualAndAnoFalescimentoGreaterThanEqual(ano, ano);
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepositorio.
                findByIdiomaContainingIgnoreCase(idioma);
    }
}
