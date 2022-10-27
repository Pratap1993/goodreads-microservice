package com.chagu.bookinfoservice.service;

import com.chagu.bookinfoservice.dto.request.BookAuthorRequest;
import com.chagu.bookinfoservice.dto.response.AuthorDetailsResponse;
import com.chagu.bookinfoservice.mapper.EntityMapper;
import com.chagu.bookinfoservice.model.BookAuthor;
import com.chagu.bookinfoservice.repository.BookAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAuthorService {

    private final BookAuthorRepository bookAuthorRepository;
    private final EntityMapper entityMapper;

    public BookAuthorService(BookAuthorRepository bookAuthorRepository, EntityMapper entityMapper) {
        this.bookAuthorRepository = bookAuthorRepository;
        this.entityMapper = entityMapper;
    }

    public List<AuthorDetailsResponse> getAllAuthors() {
        List<BookAuthor> authors = bookAuthorRepository.findAll();
        return authors.stream().map(entityMapper::mapAuthorToAuthorDetailsResponse).toList();
    }

    public AuthorDetailsResponse getBookAuthorByName(String bookAuthorName) {
        BookAuthor bookAuthor = bookAuthorRepository.findByAuthorName(bookAuthorName).orElse(null);
        return entityMapper.mapAuthorToAuthorDetailsResponse(bookAuthor);
    }

    public AuthorDetailsResponse getBookAuthorByEmail(String bookAuthorEmail) {
        return bookAuthorRepository.findByAuthorEmail(bookAuthorEmail).map(entityMapper::mapAuthorToAuthorDetailsResponse).orElse(null);
    }

    public AuthorDetailsResponse saveBookAuthor(BookAuthorRequest bookAuthorRequest) {
        return entityMapper.mapAuthorToAuthorDetailsResponse(persistBookAuthor(bookAuthorRequest));
    }

    protected void updateBookAuthor(BookAuthor payloadAuthor) {
        bookAuthorRepository.saveAndFlush(payloadAuthor);
    }

    protected BookAuthor findBookAuthorById(Integer bookAuthorId) {
        return bookAuthorRepository.findById(bookAuthorId).orElse(null);
    }

    protected BookAuthor findBookAuthorByName(String bookAuthorName) {
        return bookAuthorRepository.findByAuthorName(bookAuthorName).orElse(null);
    }

    protected BookAuthor persistBookAuthor(BookAuthorRequest bookAuthorRequest) {
        BookAuthor author = new BookAuthor();
        author.setAuthorName(bookAuthorRequest.getAuthorName());
        author.setAuthorEmail(bookAuthorRequest.getAuthorEmail());
        return bookAuthorRepository.save(author);
    }
}
