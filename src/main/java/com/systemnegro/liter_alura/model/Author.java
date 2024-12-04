package com.systemnegro.liter_alura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public Author(AuthorApiResponse author) {
        this.books = new ArrayList<>();
        this.name = author.name();
        this.birthYear = author.birthYear();
        this.deathYear = author.deathYear();
    }

    public void addBook(Book book) {
        this.books.add(book);
        book.setAuthor(this);
    }

    @Override
    public String toString() {
        return "Author{"+
                ", books=" + books +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }
}
