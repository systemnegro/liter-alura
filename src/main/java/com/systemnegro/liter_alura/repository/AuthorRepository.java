package com.systemnegro.liter_alura.repository;

import com.systemnegro.liter_alura.model.Author;
import com.systemnegro.liter_alura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select b from Author a join a.books b")
    List<Book> findAllBook();

    @Query("select b from Author a JOIN a.books b WHERE b.title ILIKE %:title%")
    Optional<Book> findByTitle(String title);

    Optional<Author> findByNameContainingIgnoreCase(String name);
}
