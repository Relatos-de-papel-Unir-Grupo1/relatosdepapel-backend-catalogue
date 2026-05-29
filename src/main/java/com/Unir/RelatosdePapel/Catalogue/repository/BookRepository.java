package com.Unir.RelatosdePapel.Catalogue.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.Unir.RelatosdePapel.Catalogue.repository.model.Book;
import com.Unir.RelatosdePapel.Catalogue.repository.predicate.SearchCriteria;
import com.Unir.RelatosdePapel.Catalogue.repository.predicate.SearchFields;
import com.Unir.RelatosdePapel.Catalogue.repository.predicate.SearchOperation;
import com.Unir.RelatosdePapel.Catalogue.repository.predicate.SearchStatement;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final BookJpaRepository bookJpaRepository;

    public List<Book> findBooks(String title, String author, String isbn, String category, Double price,
            Integer pageSize, Integer page) {
        SearchCriteria<Book> spec = new SearchCriteria<>();

        if (StringUtils.hasText(title)) {
            spec.add(new SearchStatement(SearchFields.TITLE, title, SearchOperation.MATCH));
        }

        if (StringUtils.hasText(author)) {
            spec.add(new SearchStatement(SearchFields.AUTHOR, author, SearchOperation.MATCH));
        }

        if (StringUtils.hasText(isbn)) {
            spec.add(new SearchStatement(SearchFields.ISBN, isbn, SearchOperation.EQUAL));
        }

        if (StringUtils.hasText(category)) {
            spec.add(new SearchStatement(SearchFields.CATEGORY, category, SearchOperation.EQUAL));
        }

        if (price != null && price > 0) {
            spec.add(new SearchStatement(SearchFields.PRICE, price, SearchOperation.LESS_THAN_EQUAL));
        }

        return bookJpaRepository.findAll(spec, Pageable.ofSize(pageSize).withPage(page)).getContent();

    }

    public List<Book> findbooks(Integer size, Integer page) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page number must be non-negative and size must be positive.");
        }
        return bookJpaRepository.findAll(Pageable.ofSize(size).withPage(page)).getContent();
    }
}
