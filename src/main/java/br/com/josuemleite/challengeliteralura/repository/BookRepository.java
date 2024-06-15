package br.com.josuemleite.challengeliteralura.repository;

import br.com.josuemleite.challengeliteralura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Optional<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.language = :language")
    List<Book> findByLanguage(String language);
}
