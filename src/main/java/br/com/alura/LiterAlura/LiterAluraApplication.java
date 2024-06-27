package br.com.alura.LiterAlura;

import br.com.alura.LiterAlura.main.Principal;
import br.com.alura.LiterAlura.repository.AutorRepository;
import br.com.alura.LiterAlura.repository.LivroRepository;
import br.com.alura.LiterAlura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
    @Autowired
    private LivroRepository repositorioLivro;
    @Autowired
    private AutorRepository repositorioAutor;
    @Autowired
    private LivroService livroService;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        try {
            System.out.println("Hello World, by Renthon34!");
            Principal principal = new Principal(livroService);
            principal.exibeMenu();
        } catch (InputMismatchException e){
            System.out.println("Valor inserido inválido. Desligando...");
        }
	}
}
