package com.systemnegro.liter_alura.repository;

import com.systemnegro.liter_alura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
