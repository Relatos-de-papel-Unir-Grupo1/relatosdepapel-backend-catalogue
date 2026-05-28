package com.Unir.RelatosdePapel.Catalogue.controller.model;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "books",       
})

@Getter
@Setter
@Builder
public class GetBooksResponseDto implements Serializable {
    
    private final static long serialVersionUID = 8761235707215843524L;

    @JsonProperty("books")
    public List<BookDto> books;
}
