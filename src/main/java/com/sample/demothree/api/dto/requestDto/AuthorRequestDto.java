package com.sample.demothree.api.dto.requestDto;

import com.sample.demothree.model.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class AuthorRequestDto {

    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;
    private String address;
    private List<Integer> bookList = new ArrayList<>();
}
