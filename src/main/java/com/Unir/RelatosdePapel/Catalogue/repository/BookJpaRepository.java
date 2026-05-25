package com.Unir.RelatosdePapel.Catalogue.repository;

import org.springframework.stereotype.Repository;
import com.Unir.RelatosdePapel.Catalogue.repository.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface BookJpaRepository extends 
    JpaRepository<Book, Integer> {
         
}
