package com.systemnegro.liter_alura;


import com.systemnegro.liter_alura.model.ApiResponse;
import com.systemnegro.liter_alura.model.Author;
import com.systemnegro.liter_alura.model.Book;
import com.systemnegro.liter_alura.repository.AuthorRepository;
import com.systemnegro.liter_alura.service.ApiConsumption;
import com.systemnegro.liter_alura.service.JsonSerializationService;

import java.util.Scanner;

public class Main {
    private final ApiConsumption consumer = new ApiConsumption();
    private final JsonSerializationService serialization = new JsonSerializationService();
    private final AuthorRepository repository;
    private final Scanner scanner = new Scanner(System.in);

    public Main(AuthorRepository repository) {
        this.repository = repository;
    }

    public void menu() {
        var option = -1;
        while (option != 0) {
            System.out.println("""
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    0 - Sair
                    """);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> searchBookByTitle();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void searchBookByTitle() {
        System.out.println("Insira o nome do livro que deseja procurar: ");
        var desiredBook = scanner.nextLine();
        String apiAddress = "https://gutendex.com/books/?search=";
        var json = consumer.consumption(apiAddress + desiredBook.replace(" ", "%20").trim());
        var obj = serialization.fromJson(json, ApiResponse.class);

        Book book = new Book(obj.results().getFirst());
        Author author = new Author(obj.results().getFirst().authors().getFirst());
        author.addBook(book);

        repository.save(author);
    }
}
