package com.Unir.RelatosdePapel.Catalogue.controller.model;

import java.io.Serializable;

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
    "isbn"    
})

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE )
public class WriteBookRequestDto implements Serializable {

    private final static long serialVersionUID = 1L;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;
    
    @JsonProperty("author")
    private String author;

    @JsonProperty("publicationDate")
    private String publicationDate;

    @JsonProperty("isbn")
    private String isbn;    
}

