package com.Unir.RelatosdePapel.Catalogue.service;

import org.springframework.stereotype.Service;

import com.Unir.RelatosdePapel.Catalogue.controller.model.GetBookResponseDto;
import com.Unir.RelatosdePapel.Catalogue.controller.model.WriteBookRequestDto;
import com.Unir.RelatosdePapel.Catalogue.repository.BookJpaRepository;
import com.Unir.RelatosdePapel.Catalogue.repository.model.Book;
import com.Unir.RelatosdePapel.Catalogue.utils.BookMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateBooksService {
    private final BookJpaRepository bookJpaRepository;
    private final BookMapper bookMapper;

    @Transactional
    public GetBookResponseDto createBook(WriteBookRequestDto request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publicationDate(request.getPublicationDate())
                .isbn(request.getIsbn())
                .build();

        var savedBook = bookJpaRepository.save(book);

        return bookMapper.toGetBookResponseDto(savedBook);
    }

}
