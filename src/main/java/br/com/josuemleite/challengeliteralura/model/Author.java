package br.com.josuemleite.challengeliteralura.model;

import br.com.josuemleite.challengeliteralura.model.dto.AuthorDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birthYear;
    private String deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();

    public Author(AuthorDTO authorDTO) {
        this.name = authorDTO.name();
        this.birthYear = authorDTO.birthYear();
        this.deathYear = authorDTO.deathYear();
    }

    public String getAllAuthorData() {
        String authorData = "Autor: " + this.name + " (" + this.birthYear + "-" + this.deathYear + ") " + "\n***\n";
        StringBuilder booksData = new StringBuilder();
        for (Book book : books) {
            booksData.append(book.toString()).append("\n---\n");
        }
        booksData.delete(booksData.length() - 1, booksData.length());
        return authorData + booksData;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.birthYear + "-" + this.deathYear + ")";
    }
}
