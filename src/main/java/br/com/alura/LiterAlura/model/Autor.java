package br.com.alura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @JsonAlias("name")
    private String nome;
    @JsonAlias("birth_year")
    private Integer anoNascimento;
    @JsonAlias("death_year")
    private Integer anoFalescimento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor() {}
    public Autor(String nome, Integer anoNascimento, Integer anoFalescimento, List<Livro> livros) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoFalescimento = anoFalescimento;
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalescimento() {
        return anoFalescimento;
    }

    public void setAnoFalescimento(Integer anoFalescimento) {
        this.anoFalescimento = anoFalescimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nome='" + nome + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", anoFalescimento=" + anoFalescimento +
                ", livros=" + livros +
                '}';
    }
}