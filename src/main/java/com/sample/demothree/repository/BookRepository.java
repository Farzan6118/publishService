package com.sample.demothree.repository;

import com.sample.demothree.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAllBy();
}
