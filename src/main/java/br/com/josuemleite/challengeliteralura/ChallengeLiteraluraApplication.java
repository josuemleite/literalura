package br.com.josuemleite.challengeliteralura;

import br.com.josuemleite.challengeliteralura.repository.AuthorRepository;
import br.com.josuemleite.challengeliteralura.repository.BookRepository;
import br.com.josuemleite.challengeliteralura.ui.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Application application = new Application(authorRepository, bookRepository);
		application.run();
	}
}
