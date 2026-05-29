package com.Unir.RelatosdePapel.Catalogue.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.Unir.RelatosdePapel.Catalogue.controller.model.BookDto;
import com.Unir.RelatosdePapel.Catalogue.controller.model.GetBookResponseDto;
import com.Unir.RelatosdePapel.Catalogue.controller.model.WriteBookRequestDto;
import com.Unir.RelatosdePapel.Catalogue.repository.model.Book;

import lombok.RequiredArgsConstructor;



@Component
@RequiredArgsConstructor
public class BookMapper {

    public GetBookResponseDto toGetBookResponseDto(Book book) {
        return GetBookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationDate(book.getPublicationDate())
                .isbn(book.getIsbn())
                .category(book.getCategory())
                .visibility(book.getVisibility())
                .unitPrice(book.getUnitPrice())
                .stock(book.getStock())
                .rating(book.getRating())
                .build();
    }   

    public List<BookDto> toBookDtoList(List<Book> books) {
        return books.stream()
                .map(book -> BookDto.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .isbn(book.getIsbn())
                        .category(book.getCategory())
                        .visibility(book.getVisibility())
                        .unitPrice(book.getUnitPrice())
                        .stock(book.getStock())
                        .build())
                .toList();
    }

    public Book toBook(Long bookId, WriteBookRequestDto bookDto) {
        return Book.builder()
                .id(bookId)
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publicationDate(bookDto.getPublicationDate())
                .isbn(bookDto.getIsbn())
                .category(bookDto.getCategory())
                .visibility(bookDto.getVisibility())
                .unitPrice(bookDto.getUnitPrice())
                .stock(bookDto.getStock())
                .rating(bookDto.getRating())
                .build();
    }

    public Book toBook(GetBookResponseDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publicationDate(bookDto.getPublicationDate())
                .isbn(bookDto.getIsbn())
                .category(bookDto.getCategory())
                .visibility(bookDto.getVisibility())
                .unitPrice(bookDto.getUnitPrice())
                .stock(bookDto.getStock())
                .rating(bookDto.getRating())
                .build();
    }


}