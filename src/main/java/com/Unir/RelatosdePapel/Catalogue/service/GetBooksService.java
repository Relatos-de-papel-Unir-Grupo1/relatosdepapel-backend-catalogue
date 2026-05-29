package com.Unir.RelatosdePapel.Catalogue.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.Unir.RelatosdePapel.Catalogue.controller.model.GetBookResponseDto;
import com.Unir.RelatosdePapel.Catalogue.controller.model.GetBooksResponseDto;
import com.Unir.RelatosdePapel.Catalogue.exception.BookNotFoundException;
import com.Unir.RelatosdePapel.Catalogue.repository.BookJpaRepository;
import com.Unir.RelatosdePapel.Catalogue.repository.BookRepository;
import com.Unir.RelatosdePapel.Catalogue.repository.model.Book;
import com.Unir.RelatosdePapel.Catalogue.utils.BookMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetBooksService {
    private final BookRepository bookRepository;
    private final BookJpaRepository bookJpaRepository;
    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    public GetBookResponseDto getBook(Long id) {
        var book = bookJpaRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return bookMapper.toGetBookResponseDto(book);
    }

    @Transactional(readOnly = true)
    public GetBooksResponseDto getBooks(String title, String author, String isbn, String category, Double price, Integer pageSize, Integer page) {
        List<Book> books;
        if (StringUtils.hasLength(title) || StringUtils.hasLength(author) || StringUtils.hasLength(isbn) || StringUtils.hasLength(category) || price != null) {
            books = bookRepository.findBooks(title, author, isbn, category, price, pageSize, page);
        } else {
            books = bookRepository.findbooks(pageSize, page);
        }

        return GetBooksResponseDto.builder()
                .books(bookMapper.toBookDtoList(books))
                .build();
    }
}
