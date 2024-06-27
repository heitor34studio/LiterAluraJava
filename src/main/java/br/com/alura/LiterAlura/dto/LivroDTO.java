package br.com.alura.LiterAlura.dto;

import br.com.alura.LiterAlura.model.Autor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(@JsonAlias("title")String titulo,
                       @JsonAlias("languages")List<String> idioma,
                       @JsonAlias("download_count")Double downloads,
                       @JsonAlias("authors")List<Autor> autor) {
}
