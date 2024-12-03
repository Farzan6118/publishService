package com.sample.demothree.service;

import com.sample.demothree.api.dto.requestDto.AuthorRequestDto;
import com.sample.demothree.api.dto.requestDto.BookRequestDto;
import com.sample.demothree.model.Author;
import com.sample.demothree.model.Book;
import com.sample.demothree.repository.AuthorRepository;
import com.sample.demothree.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublishService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public PublishService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    public void addSampleData() {
        List<Book> books = new ArrayList<>();

        Book book1 = new Book();
        book1.setTitle("asd");
        book1.setIsbn("123");

        Book book2 = new Book();
        book2.setTitle("qwe");
        book2.setIsbn("456");

        books.add(book1);
        books.add(book2);

        bookRepository.saveAll(books);

        Author author = new Author();
        author.setName("John Doe");
        author.setAge(29);
        author.setEmail("john.doe@example.com");
        author.setAddress("new address");
        author.setSurname("new surname");
        author.setPhone("1234567890");

        Author save = authorRepository.save(author);
        book1.setAuthor(save);
        book2.setAuthor(save);
        bookRepository.saveAll(books);
    }

    public void removeAllData() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    public void removeBookById(int id) {
        bookRepository.deleteById(id);

    }

    public void removeAuthorById(int id) {
        authorRepository.deleteById(id);
    }

    public void addAuthor(AuthorRequestDto dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setAge(dto.getAge());
        author.setEmail(dto.getEmail());
        author.setAddress(dto.getAddress());
        author.setSurname(dto.getSurname());
        author.setPhone(dto.getPhone());
        Iterable<Book> allById = bookRepository.findAllById(dto.getBookList());
        List<Book> bookList = new ArrayList<>();
        while (allById.iterator().hasNext()) {
            Book book = allById.iterator().next();
            bookList.add(book);
        }
        author.setBookList(bookList);
        authorRepository.save(author);
    }

    public void addBook(BookRequestDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        Optional<Author> authorById = authorRepository.findById(dto.getAuthor());
        authorById.ifPresent(book::setAuthor);
        bookRepository.save(book);
    }
}
