package com.Unir.RelatosdePapel.Catalogue.repository.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "author", nullable = false, length = 255)
    private String author;

    @Column(name = "publicationDate", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    @Column(name = "visibility", nullable = false)
    private Boolean visibility;

    @Column(name = "unitPrice", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;
    
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "rating", nullable = true)
    private Double rating;

    @CreationTimestamp
    @Column(name = "createTime", nullable = false)
    private Instant createTime;
}
