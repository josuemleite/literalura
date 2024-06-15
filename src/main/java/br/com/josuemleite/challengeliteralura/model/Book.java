package br.com.josuemleite.challengeliteralura.model;

import br.com.josuemleite.challengeliteralura.model.dto.BookDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private String language;
    private Integer downloadsNumber;

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.title();
        this.language = bookDTO.languages().get(0);
        this.downloadsNumber = bookDTO.downloadsNumber();
    }

    @Override
    public String toString() {
        return "Título: " + this.title + "\n" +
                "Idioma: " + this.language + "\n" +
                "Número de downloads: " + this.downloadsNumber;
    }
}
