package com.sample.demothree.api.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String isbn;
    private int author;
}
