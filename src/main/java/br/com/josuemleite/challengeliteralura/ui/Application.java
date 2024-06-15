package br.com.josuemleite.challengeliteralura.ui;

import br.com.josuemleite.challengeliteralura.model.Author;
import br.com.josuemleite.challengeliteralura.model.Book;
import br.com.josuemleite.challengeliteralura.model.dto.AuthorDTO;
import br.com.josuemleite.challengeliteralura.model.dto.BookDTO;
import br.com.josuemleite.challengeliteralura.model.dto.ResultsDTO;
import br.com.josuemleite.challengeliteralura.repository.AuthorRepository;
import br.com.josuemleite.challengeliteralura.repository.BookRepository;
import br.com.josuemleite.challengeliteralura.service.ConsumerApi;
import br.com.josuemleite.challengeliteralura.service.DataConverter;

import java.util.*;

public class Application {
    private final Scanner scanner = new Scanner(System.in);

    private final ConsumerApi consumerApi = new ConsumerApi();
    private final DataConverter converter = new DataConverter();

    private final String ADDRESS = "https://gutendex.com/books?search=";

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private List<Author> authors = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    public Application(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void run() {
        while (true) {
            Integer option = menu();
            if (Objects.isNull(option)) {
                continue;
            }
            switch (option) {
                case 1:
                    String search = searchBookByTitle();
                    ResultsDTO dataFromApi = getDataFromApi(search);
                    if (dataFromApi.books().isEmpty()) {
                        System.out.println("Nenhum dado encontrado...");
                        systemPause();
                        continue;
                    }
                    BookDTO bookDTO = dataFromApi.books().get(0);
                    System.out.println(bookDTO);
                    saveData(dataFromApi);
                    systemPause();
                    break;
                case 2:
                    listSavedBooks();
                    systemPause();
                    break;
                case 3:
                    listSavedAuthors();
                    systemPause();
                    break;
                case 4:
                    listSavedAuthorsAndTheirBooks();
                    systemPause();
                    break;
                case 5:
                    searchAuthorsAlive();
                    systemPause();
                    break;
                case 6:
                    searchBooksByLanguage();
                    systemPause();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Insira um valor correspondente a alguma opção do menu!");
                    break;
            }
        }
    }

    private void systemPause() {
        System.out.print("Pressione a tecla enter (entrada) para continuar...");
        scanner.nextLine();
    }

    private Integer handleNumberInput(String input) {
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, insira um número válido.");
            systemPause();
            return null;
        }
        return number;
    }

    private Integer menu() {
        System.out.println("==============================================");
        System.out.println("[1] Buscar livro pelo título");
        System.out.println("[2] Listar livros registrados");
        System.out.println("[3] Listar autores registrados");
        System.out.println("[4] Listar autores registrados com seus respectivos livros");
        System.out.println("[5] Listar autores vivos em um determinado ano");
        System.out.println("[6] Listar livros em um determinado idioma");
        System.out.println("[0] Sair");
        System.out.println("Insira abaixo o valor correspondente à opção desejada");
        System.out.print("> ");
        return handleNumberInput(scanner.nextLine());
    }

    private String searchBookByTitle() {
        while (true) {
            System.out.println("A busca pode ser feita pelo título junto ao nome do autor.");
            System.out.println("Por exemplo: machado memorias");
            System.out.println("(Machado de Assis, Memorias Phostumas...)");
            String search = scanner.nextLine();
            if (search.isBlank()) {
                System.out.println("Realize uma pesquisa válida!\n");
                continue;
            }
            return search;
        }
    }

    private ResultsDTO getDataFromApi(String search) {
        String json = consumerApi.obtainData(ADDRESS + search.replace(" ", "+"));
        return converter.obtainData(json, ResultsDTO.class);
    }

    private void saveData(ResultsDTO resultsDTO) {
        BookDTO bookDTO = resultsDTO.books().get(0);

        Optional<Book> existingBook = bookRepository.findByTitle(bookDTO.title());

        if (existingBook.isPresent()) {
            return;
        }

        AuthorDTO authorDTO = bookDTO.authors().get(0);

        Optional<Author> existingAuthor = authorRepository.findByName(authorDTO.name());

        Author author = existingAuthor.orElseGet(() -> new Author(authorDTO));

        Book book = new Book(bookDTO);
        book.setAuthor(author);
        author.getBooks().add(book);

        authorRepository.save(author);
    }

    private void listSavedAuthors() {
        authors = authorRepository.findAll();
        authors.stream()
                .sorted(Comparator.comparing(Author::getName))
                .forEach(author -> {
                    System.out.println(author);
                    System.out.println("---");
                });
    }

    private void listSavedBooks() {
        books = bookRepository.findAll();
        books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(book -> {
                    System.out.println(book);
                    System.out.println("---");
                });
    }

    private void listSavedAuthorsAndTheirBooks() {
        authors = authorRepository.findAll();
        authors.stream()
                .sorted(Comparator.comparing(Author::getName))
                .forEach(author -> {
                    System.out.println(author.getAllAuthorData());
                });
    }

    private void searchAuthorsAlive() {
        while (true) {
            System.out.println("Informe o ano (por exemplo, 1889):");
            System.out.print("> ");
            Integer year = handleNumberInput(scanner.nextLine());
            if (Objects.isNull(year)) {
                continue;
            }
            listAuthorsAliveInYear(year);
            return;
        }
    }

    private void listAuthorsAliveInYear(Integer year) {
        List<Author> authorsAliveInYear = authorRepository.findAuthorsAliveInYear(year);
        if (!authorsAliveInYear.isEmpty()) {
            authorsAliveInYear.stream()
                    .sorted(Comparator.comparing(Author::getName))
                    .forEach(author -> {
                        System.out.println(author.getAllAuthorData());
                    });
        } else {
            System.out.println("Nenhum autor registrado estava vivo em " + year + "...");
        }
    }

    private void searchBooksByLanguage() {
        while (true) {
            System.out.println("Insira os dois caracteres do idioma para realizar a busca:");
            System.out.println("Abaixo segue uma lista de possíveis idiomas, mas sinta-se à vontade para pesquisar por outros.");
            System.out.println("es - Espanhol");
            System.out.println("en - Inglês");
            System.out.println("fr - Francês");
            System.out.println("pt - Português");
            System.out.print("> ");
            String language = scanner.nextLine();
            if (language.isBlank() || !language.chars().allMatch(Character::isLetter)) {
                System.out.println("Digite um idioma válido para a pesquisa!");
                systemPause();
                continue;
            }
            listBooksByLanguage(language);
            return;
        }
    }

    private void listBooksByLanguage(String language) {
        List<Book> booksInLanguage = bookRepository.findByLanguage(language);
        if (!booksInLanguage.isEmpty()) {
            booksInLanguage.stream()
                    .sorted(Comparator.comparing(Book::getTitle))
                    .forEach(book -> {
                        System.out.println(book);
                        System.out.println("---");
                    });
        } else {
            System.out.println("Nenhum livro encontrado no idioma [" + language + "]...");
        }
    }
}
