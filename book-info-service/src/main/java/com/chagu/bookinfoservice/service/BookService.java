package com.chagu.bookinfoservice.service;

import com.chagu.bookinfoservice.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookService {

    private static Map<String, Book> bookMap = null;

    public BookService() {
        List<Book> bookList = Arrays.asList(
                new Book("1", "Half Girlfriend", "Chetan Bhagat", 150),
                new Book("2", "The Bollywood Bride", "Sonal Dev", 175),
                new Book("3", "The One You Cannot Have", "Preeti Shenoy", 100),
                new Book("4", "I Too Had a Love Story", "Ravinder Singh", 400),
                new Book("5", "Everyone Has A Story", "Savi Sharma", 150),
                new Book("6", "The Girl of My Dreams", "Durjoy Datta", 300)
        );
        bookMap = bookList.stream().collect(Collectors.toMap(Book::getBookId, Function.identity()));
    }

    public Book getById(String bookId) {
        Optional<Book> bookOpt = bookMap.entrySet().stream().filter(map -> map.getKey().equals(bookId))
                .map(Map.Entry::getValue).findAny();
        return bookOpt.orElse(null);
    }

    public Book getByName(String bookName) {
        Optional<Book> bookOpt = bookMap.values().stream().filter(book -> book.getBookName().equals(bookName)).findAny();
        return bookOpt.orElse(null);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookMap.values());
    }

    public List<Book> getAllBooksByIds(String bookIds) {
        if (Objects.isNull(bookIds))
            return null;
        String ids = bookIds.replaceAll("\\s", "").replace("[", "").replace("]", "");
        List<String> bookIdList = Arrays.asList(ids.split(","));
        return bookMap.values().stream().filter(book -> bookIdList.contains(book.getBookId())).toList();
    }
}
