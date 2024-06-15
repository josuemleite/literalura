package br.com.josuemleite.challengeliteralura.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(@JsonAlias("title") String title,
                      @JsonAlias("authors") List<AuthorDTO> authors,
                      @JsonAlias("languages") List<String> languages,
                      @JsonAlias("download_count") Integer downloadsNumber) {

    @Override
    public String toString() {
        return "Título: " + this.title + "\n" +
                "Idioma: " + this.languages.get(0) + "\n" +
                this.authors.toString() + "\n" +
                "Número de downloads: " + this.downloadsNumber;
    }
}
