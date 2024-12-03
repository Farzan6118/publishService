package com.sample.demothree.api;

import com.sample.demothree.api.dto.requestDto.AuthorRequestDto;
import com.sample.demothree.api.dto.requestDto.BookRequestDto;
import com.sample.demothree.api.dto.responseDto.AuthorResponseDto;
import com.sample.demothree.api.dto.responseDto.BookResponseDto;
import com.sample.demothree.service.PublishService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class AuthorController {

    private final PublishService publishService;

    public AuthorController(PublishService publishService) {
        this.publishService = publishService;
    }

    @DeleteMapping("delete-all")
    public ResponseEntity<String> removeAllData() {
        publishService.removeAllData();
        return ResponseEntity.ok("All data removed successfully");
    }

    @PostMapping("authors")
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody AuthorRequestDto dto) {
        AuthorResponseDto responseDto = publishService.addAuthor(dto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("books")
    public ResponseEntity<BookResponseDto> addBook(@RequestBody BookRequestDto dto) {
        BookResponseDto responseDto = null;
        try {
            responseDto = publishService.addBook(dto);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("authors")
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors() {
        List<AuthorResponseDto> responseDto = publishService.getAllAuthors();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("books")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> responseDto = publishService.getAllBooks();
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("authors/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int id) {
        try {
            publishService.deleteAuthor(id);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Author with ID " + id + " deleted successfully");
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        publishService.deleteBook(id);
        return ResponseEntity.ok("Book with ID " + id + " deleted successfully");
    }
}
