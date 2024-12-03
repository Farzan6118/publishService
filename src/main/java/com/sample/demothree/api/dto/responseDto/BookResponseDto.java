package com.sample.demothree.api.dto.responseDto;

import com.sample.demothree.model.Author;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookResponseDto {

    private int id;
    private String title;
    private String isbn;
//    private Author author;
}
