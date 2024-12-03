package com.sample.demothree.api.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class AuthorRequestDto {

    @Positive
    private int age;
    @NotBlank
    private String name;
    private String surname;
    @NotBlank
    @Email
    private String email;
    private String phone;
    private String address;
    private List<Integer> bookList = new ArrayList<>();
}
