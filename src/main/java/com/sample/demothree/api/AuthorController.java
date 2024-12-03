package com.sample.demothree.api;

import com.sample.demothree.api.dto.requestDto.AuthorRequestDto;
import com.sample.demothree.api.dto.requestDto.BookRequestDto;
import com.sample.demothree.service.PublishService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class AuthorController {

    private final PublishService publishService;

    public AuthorController(PublishService publishService) {
        this.publishService = publishService;
    }

    @PostMapping("addDummyData")
    public void AddSampleData() {
        publishService.addSampleData();
    }

    @PostMapping("deleteAll")
    public void removeAllData() {
        publishService.removeAllData();
    }

    @PostMapping("add/author")
    public void removeAuthorById(@RequestBody AuthorRequestDto dto) {
        publishService.addAuthor(dto);
    }

    @PostMapping("add/book")
    public void removeAuthorById(@RequestBody BookRequestDto dto) {
        publishService.addBook(dto);
    }


    @DeleteMapping("delete/author/{id}")
    public void removeAuthorById(@PathVariable int id) {
        publishService.removeAuthorById(id);
    }

    @DeleteMapping("delete/book/{id}")
    public void removeBookById(@PathVariable int id) {
        publishService.removeBookById(id);
    }
}
