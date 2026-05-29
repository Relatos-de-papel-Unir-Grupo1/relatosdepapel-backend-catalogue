package com.Unir.RelatosdePapel.Catalogue.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Unir.RelatosdePapel.Catalogue.controller.model.GetBookResponseDto;
import com.Unir.RelatosdePapel.Catalogue.controller.model.WriteBookRequestDto;
import com.Unir.RelatosdePapel.Catalogue.exception.BookNotFoundException;
import com.Unir.RelatosdePapel.Catalogue.repository.BookJpaRepository;
import com.Unir.RelatosdePapel.Catalogue.repository.model.Book;
import com.Unir.RelatosdePapel.Catalogue.utils.BookMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ModifyBookService {
    private final BookJpaRepository bookJpaRepository;
    private final BookMapper bookMapper;
    private final ObjectMapper objectMapper;

    @Transactional
    public GetBookResponseDto modifyBook(Long bookId, WriteBookRequestDto bookDto) {
        Book modifiedBook = bookMapper.toBook(bookId, bookDto);
        modifiedBook.setId(bookId);
        var updatedBook = bookJpaRepository.save(modifiedBook);
        return bookMapper.toGetBookResponseDto(updatedBook);
    }

    @Transactional
    public GetBookResponseDto modifyBook(Long bookId, String jsonPart) {
        var book = bookMapper.toGetBookResponseDto(bookJpaRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId)));
        try {
            JsonNode patch = objectMapper.readTree(jsonPart);
            JsonNode actualBook = objectMapper.valueToTree(book);
            JsonMergePatch mergePatch = JsonMergePatch.fromJson(patch);

            JsonNode patchedBookNode = mergePatch.apply(actualBook);
            GetBookResponseDto patchedBook = objectMapper.treeToValue(patchedBookNode, GetBookResponseDto.class);
            patchedBook.setId(bookId);
            Book savedBook = bookJpaRepository.save(bookMapper.toBook(patchedBook));
            return bookMapper.toGetBookResponseDto(savedBook);

        } catch (JsonProcessingException | JsonPatchException e) {
            log.error("Error processing JSON patch for book ID {}: {}", bookId, e.getMessage(), e);
            throw new RuntimeException("Error processing JSON patch", e);
        }
    }
}
