package com.chagu.bookinfoservice.service;

import com.chagu.bookinfoservice.dto.request.BookAuthorRequest;
import com.chagu.bookinfoservice.dto.request.BookRequest;
import com.chagu.bookinfoservice.dto.response.BookDetailsResponse;
import com.chagu.bookinfoservice.exception.BookAlreadyExistsException;
import com.chagu.bookinfoservice.exception.ExceptionMessage;
import com.chagu.bookinfoservice.exception.MissingFieldException;
import com.chagu.bookinfoservice.mapper.EntityMapper;
import com.chagu.bookinfoservice.model.Book;
import com.chagu.bookinfoservice.model.BookAuthor;
import com.chagu.bookinfoservice.repository.BookRepository;
import com.chagu.bookinfoservice.repository.CustomRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final BookAuthorService bookAuthorService;

    private final CustomRepository customRepository;

    private final EntityMapper entityMapper;

    public BookService(BookRepository bookRepository, BookAuthorService bookAuthorService, CustomRepository customRepository,
                       EntityMapper entityMapper) {
        this.bookRepository = bookRepository;
        this.bookAuthorService = bookAuthorService;
        this.customRepository = customRepository;
        this.entityMapper = entityMapper;
    }

    private Book findById(Integer bookId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        return bookOpt.orElse(null);
    }

    public BookDetailsResponse getById(Integer bookId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        return bookOpt.map(entityMapper::mapBookToBookDetailsResponse).orElse(null);
    }

    public BookDetailsResponse getByName(String bookName) {
        Optional<Book> bookOpt = bookRepository.findByBookName(bookName);
        return bookOpt.map(entityMapper::mapBookToBookDetailsResponse).orElse(null);
    }

    public List<BookDetailsResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(entityMapper::mapBookToBookDetailsResponse).toList();
    }

    public List<BookDetailsResponse> getAllBooksByIds(String bookIds) {
        if (Objects.isNull(bookIds))
            return null;
        String ids = bookIds.replaceAll("\\s", "").replace("[", "").replace("]", "");
        List<Integer> bookIdList = Arrays.stream(ids.split(",")).map(Integer::parseInt).toList();
        return customRepository.getBookListByIds(bookIdList).stream()
                .map(entityMapper::mapBookToBookDetailsResponse).toList();
    }

    public BookDetailsResponse saveBook(BookRequest bookRequest) {
        Optional<Book> bookOpt = bookRepository.findByBookName(bookRequest.getBookName());
        if (bookOpt.isPresent())
            throw new BookAlreadyExistsException(ExceptionMessage.BOOK_ALREADY_EXISTS.getExceptionMessage());
        BookAuthor bookAuthor = bookAuthorService.findBookAuthorByName(bookRequest.getAuthorName());
        if (Objects.isNull(bookAuthor)) {
            if (Objects.isNull(bookRequest.getAuthorEmail()))
                throw new MissingFieldException(String.format(ExceptionMessage.MISSING_FIELD.getExceptionMessage(), "Author Email"));
            BookAuthorRequest newBookAuthor = new BookAuthorRequest();
            newBookAuthor.setAuthorName(bookRequest.getAuthorName());
            newBookAuthor.setAuthorEmail(bookRequest.getAuthorEmail());
            bookAuthor = bookAuthorService.persistBookAuthor(newBookAuthor);
        }
        Book newBook = new Book();
        newBook.setBookName(bookRequest.getBookName());
        newBook.setBookPrice(bookRequest.getBookPrice());
        newBook.setBookAuthor(bookAuthor);
        return entityMapper.mapBookToBookDetailsResponse(persistBook(newBook));
    }

    private Book persistBook(Book newBook) {
        return bookRepository.save(newBook);
    }
}
