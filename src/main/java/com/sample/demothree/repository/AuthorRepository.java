package com.sample.demothree.repository;

import com.sample.demothree.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    List<Author> findAllByIdIn(List<Integer> list);
}
