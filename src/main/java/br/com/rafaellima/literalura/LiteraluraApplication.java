package br.com.rafaellima.literalura;

import br.com.rafaellima.literalura.repository.AutorRepository;
import br.com.rafaellima.literalura.repository.LivroRepository;
import br.com.rafaellima.literalura.view.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		App app = new App(
				livroRepository,
				autorRepository
		);
		app.menu();
	}
}
