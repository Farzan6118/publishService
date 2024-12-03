package com.sample.demothree.api.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRequestDto {
    private String title;
    private Integer author;
    private String isbn;
}
