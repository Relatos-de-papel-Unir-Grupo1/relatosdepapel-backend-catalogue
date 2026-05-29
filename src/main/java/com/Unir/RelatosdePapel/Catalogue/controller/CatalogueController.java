package com.Unir.RelatosdePapel.Catalogue.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Unir.RelatosdePapel.Catalogue.controller.model.GetBookResponseDto;
import com.Unir.RelatosdePapel.Catalogue.controller.model.GetBooksResponseDto;
import com.Unir.RelatosdePapel.Catalogue.controller.model.WriteBookRequestDto;
import com.Unir.RelatosdePapel.Catalogue.service.CreateBooksService;
import com.Unir.RelatosdePapel.Catalogue.service.DeleteBooksService;
import com.Unir.RelatosdePapel.Catalogue.service.GetBooksService;
import com.Unir.RelatosdePapel.Catalogue.service.ModifyBookService;

import lombok.RequiredArgsConstructor;





@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CatalogueController {
    private final GetBooksService getBooksService;
    private final CreateBooksService createBookService;
    private final ModifyBookService modifyBookService;
    private final DeleteBooksService deleteBookService;

    @GetMapping("books")
    public ResponseEntity<GetBooksResponseDto> getBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(getBooksService.getBooks(title, author, isbn, category, price, pageSize, page));
    }
    
    @GetMapping("books/{bookId}")
    public ResponseEntity<GetBookResponseDto> getBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(getBooksService.getBook(bookId));
    }

    @PostMapping("books")
    public ResponseEntity<GetBookResponseDto> createBook(@RequestBody WriteBookRequestDto request) {
        return ResponseEntity.ok(createBookService.createBook(request));
    }

    @PutMapping("books/{bookId}")
    public ResponseEntity<GetBookResponseDto> modifyBook(@PathVariable Long bookId, @RequestBody WriteBookRequestDto request) {
        return ResponseEntity.ok(modifyBookService.modifyBook(bookId, request));
    }

    @PatchMapping("books/{bookId}")
    public ResponseEntity<GetBookResponseDto> modifyBook(@PathVariable Long bookId, @RequestBody String jsonPart) {
        return ResponseEntity.ok(modifyBookService.modifyBook(bookId, jsonPart));
    }

    @DeleteMapping("books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        deleteBookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }                
}
