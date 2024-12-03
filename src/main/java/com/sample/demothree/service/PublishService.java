package com.sample.demothree.service;

import com.sample.demothree.api.dto.requestDto.AuthorRequestDto;
import com.sample.demothree.api.dto.requestDto.BookRequestDto;
import com.sample.demothree.api.dto.responseDto.AuthorResponseDto;
import com.sample.demothree.api.dto.responseDto.BookResponseDto;
import com.sample.demothree.model.Author;
import com.sample.demothree.model.Book;
import com.sample.demothree.repository.AuthorRepository;
import com.sample.demothree.repository.BookRepository;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublishService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public PublishService(AuthorRepository authorRepository,
                          BookRepository bookRepository,
                          ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

     @Transactional
    public void removeAllData() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Transactional
    public AuthorResponseDto addAuthor(AuthorRequestDto dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setSurname(dto.getSurname());
        author.setAge(dto.getAge());
        author.setEmail(dto.getEmail());
        author.setPhone(dto.getPhone());
        author.setAddress(dto.getAddress());

        Author savedAuthor = authorRepository.save(author);
        return modelMapper.map(savedAuthor, AuthorResponseDto.class);
    }

    @Transactional
    public BookResponseDto addBook(BookRequestDto dto) throws BadRequestException {
        Optional<Author> authorOptional = authorRepository.findById(dto.getAuthor());
        if (authorOptional.isEmpty()) {
            throw new BadRequestException("Author not found with id: " + dto.getAuthor());
        }

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setAuthor(authorOptional.get());

        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookResponseDto.class);

    }

    public List<AuthorResponseDto> getAllAuthors() {
        List<Author> allAuthorList = authorRepository.findAllBy();
        return allAuthorList.stream()
                .map(author -> modelMapper.map(author, AuthorResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<BookResponseDto> getAllBooks() {
        List<Book> allBookList = bookRepository.findAllBy();
        return allBookList.stream()
                .map(book -> modelMapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAuthor(int authorId) throws BadRequestException {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isEmpty()) {
            throw new BadRequestException("Author not found with id: " + authorId);
        }

        Author author = authorOptional.get();

        author.getBookList().forEach(book -> book.setAuthor(null));
        bookRepository.saveAll(author.getBookList());

        authorRepository.delete(author);
    }

    @Transactional
    public void deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
    }
}
