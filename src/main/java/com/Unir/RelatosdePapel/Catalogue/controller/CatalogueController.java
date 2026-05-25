package com.Unir.RelatosdePapel.Catalogue.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Unir.RelatosdePapel.Catalogue.controller.model.GetBookResponseDto;
import com.Unir.RelatosdePapel.Catalogue.controller.model.WriteBookRequestDto;
import com.Unir.RelatosdePapel.Catalogue.service.CreateBooksService;
import com.Unir.RelatosdePapel.Catalogue.service.GetBooksService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CatalogueController {
    private final GetBooksService getBooksService;
    private final CreateBooksService createBookService;

    @GetMapping("books/{bookId}")
    public ResponseEntity<GetBookResponseDto> getBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(getBooksService.getBook(bookId.intValue()));
    }

    @PostMapping("books")
    public ResponseEntity<GetBookResponseDto> createBook(@RequestBody WriteBookRequestDto request) {
        return ResponseEntity.ok(createBookService.createBook(request));
    }
        
}
