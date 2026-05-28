package com.Unir.RelatosdePapel.Catalogue.controller.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "title",
    "author",
    "publicationDate",
    "isbn",
    "category",
    "visibility",
    "unitPrice",
    "stock",
    "rating"
})

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE )
public class WriteBookRequestDto implements Serializable {

    private final static long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;
    
    @JsonProperty("author")
    private String author;

    @JsonProperty("publicationDate")
    private LocalDate publicationDate;

    @JsonProperty("isbn")
    private String isbn;    

    @JsonProperty("category")
    private String category;

    @JsonProperty("visibility")
    private Boolean visibility;

    @JsonProperty("unitPrice")
    private BigDecimal unitPrice;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("rating")
    private Double rating;
}

