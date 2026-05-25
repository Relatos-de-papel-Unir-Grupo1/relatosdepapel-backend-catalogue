package com.Unir.RelatosdePapel.Catalogue.utils;

import org.springframework.stereotype.Component;
import com.Unir.RelatosdePapel.Catalogue.controller.model.GetBookResponseDto;
import com.Unir.RelatosdePapel.Catalogue.repository.model.Book;



@Component
public class BookMapper {
    
    public GetBookResponseDto toGetBookResponseDto(Book book) {
        return GetBookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationDate(book.getPublicationDate())
                .isbn(book.getIsbn())
                .build();
    }   
}
