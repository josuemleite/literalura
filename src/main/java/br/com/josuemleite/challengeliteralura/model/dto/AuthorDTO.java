package br.com.josuemleite.challengeliteralura.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDTO(@JsonAlias("name") String name,
                        @JsonAlias("birth_year") String birthYear,
                        @JsonAlias("death_year") String deathYear) {

    @Override
    public String toString() {
        return "Autor: " + this.name + " (" + this.birthYear + "-" + this.deathYear + ")";
    }
}
