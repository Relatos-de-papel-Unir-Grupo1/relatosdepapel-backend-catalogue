package com.Unir.RelatosdePapel.Catalogue.service;

import org.springframework.stereotype.Service;

import com.Unir.RelatosdePapel.Catalogue.exception.BookNotFoundException;
import com.Unir.RelatosdePapel.Catalogue.repository.BookJpaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteBooksService {
    private final BookJpaRepository bookJpaRepository;

    @Transactional
    public void deleteBook(Long bookId) {
        if (!bookJpaRepository.existsById(bookId)) {
            throw new BookNotFoundException(bookId);
        }
        bookJpaRepository.deleteById(bookId);
    }
}
