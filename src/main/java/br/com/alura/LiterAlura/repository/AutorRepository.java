package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalescimentoGreaterThanEqual(Integer ano, Integer ano2);
}
