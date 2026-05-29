package com.Unir.RelatosdePapel.Catalogue.repository;

import org.springframework.stereotype.Repository;
import com.Unir.RelatosdePapel.Catalogue.repository.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface BookJpaRepository extends 
    JpaRepository<Book, Long>,
    JpaSpecificationExecutor<Book> {

    @Query("SELECT s FROM Book s WHERE s.stock > 0")
    List<Book> findAvailableBooks();
}
