package com.Unir.RelatosdePapel.Catalogue.service;

import com.github.javafaker.Faker;
import com.Unir.RelatosdePapel.Catalogue.repository.model.Book;
import com.Unir.RelatosdePapel.Catalogue.repository.BookJpaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.seed.enabled", havingValue = "true")
public class BookDataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BookDataSeeder.class);

    private final BookJpaRepository bookRepository;

    @Value("${app.seed.books-count:20}")
    private int booksToCreate;

    @Override
    public void run(String... args) {
        if (bookRepository.count() > 0) {
            log.info("La base de datos de libros ya contiene datos. No se realizará la carga inicial.");
            return;
        }

        log.info("Iniciando carga de {} libros de prueba...", booksToCreate);

        Faker faker = new Faker(new Locale("es"));
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < booksToCreate; i++) {
            com.github.javafaker.Book fakeBook = faker.book();
            Book book = Book.builder()
                    .title(fakeBook.title())
                    .author(fakeBook.author())
                    .publicationDate(faker.date().past(10 * 365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .isbn(faker.code().isbn13())
                    .category(fakeBook.genre())
                    .visibility(true)
                    .unitPrice(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 100)))
                    .stock(faker.number().numberBetween(5, 100))
                    .rating((double) faker.number().randomDouble(1, 1, 5))
                    .build();
            books.add(book);
        }

        try {
            bookRepository.saveAll(books);
            log.info("Carga de {} libros de prueba finalizada exitosamente.", books.size());
        } catch (Exception e) {
            log.error("Error durante la carga de libros de prueba. Causa: {}", e.getMessage(), e);
        }
    }
}