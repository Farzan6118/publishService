package com.sample.demothree.repository;

import com.sample.demothree.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
