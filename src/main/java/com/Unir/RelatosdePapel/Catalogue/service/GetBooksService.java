package com.Unir.RelatosdePapel.Catalogue.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Unir.RelatosdePapel.Catalogue.controller.model.GetBookResponseDto;
import com.Unir.RelatosdePapel.Catalogue.controller.model.GetBooksResponseDto;
import com.Unir.RelatosdePapel.Catalogue.exception.BookNotFoundException;
import com.Unir.RelatosdePapel.Catalogue.repository.BookJpaRepository;
import com.Unir.RelatosdePapel.Catalogue.utils.BookMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetBooksService {
    private final BookJpaRepository bookJpaRepository;
    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    public GetBookResponseDto getBook(Long id) {
        var book = bookJpaRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return bookMapper.toGetBookResponseDto(book);
    }

    @Transactional(readOnly = true)
    public GetBooksResponseDto getBooks() {
        var books = bookJpaRepository.findAvailableBooks();
        return GetBooksResponseDto.builder()
                .books(bookMapper.toBookDtoList(books))
                .build();
    }
}
