package com.chagu.bookinfoservice.controller;

import com.chagu.bookinfoservice.dto.request.BookRequest;
import com.chagu.bookinfoservice.dto.response.BookDetailsResponse;
import com.chagu.bookinfoservice.exception.CustomExceptionBean;
import com.chagu.bookinfoservice.exception.ExceptionMessage;
import com.chagu.bookinfoservice.h2repo.BookH2Repository;
import com.chagu.bookinfoservice.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private BookH2Repository h2Repository;

    private UrlUtilForTest urlUtil;

    @BeforeEach
    void setUp() {
        urlUtil = new UrlUtilForTest(port);
        saveDefaultBook();
    }

    @Test
    void testGetAllBooks() {
        ResponseEntity<BookDetailsResponse[]> response = getResponseEntityFromAPI(urlUtil.getAllBooksUrl(),
                null, HttpMethod.GET, BookDetailsResponse[].class);
        BookDetailsResponse[] books = response.getBody();
        assertNotNull(books);
        assertEquals(books.length, h2Repository.findAll().size());
    }

    @Test
    void testGetBookById() {
        Optional<Book> bookOptional = h2Repository.findById(1);
        assertTrue(bookOptional.isPresent());
        Book bookSaved = bookOptional.get();
        ResponseEntity<BookDetailsResponse> response = getResponseEntityFromAPI(urlUtil.getBooksByIdUrl(bookSaved.getBookId()),
                null, HttpMethod.GET, BookDetailsResponse.class);
        assertNotNull(response);
        BookDetailsResponse bookDetailsResponse = response.getBody();
        assertNotNull(bookDetailsResponse);
        assertEquals(bookSaved.getBookName(), bookDetailsResponse.getBookName());
        assertEquals(bookSaved.getBookPrice().toString(), bookDetailsResponse.getBookPrice());
    }

    @Test
    void getBookByName() {
        String defaultBookName = getDefaultBookRequest().getBookName();
        Optional<Book> bookOptional = h2Repository.findByBookName(defaultBookName);
        assertTrue(bookOptional.isPresent());
        Book bookSaved = bookOptional.get();
        ResponseEntity<BookDetailsResponse> response = getResponseEntityFromAPI(urlUtil.getBookByBookNameUrl(defaultBookName),
                null, HttpMethod.GET, BookDetailsResponse.class);
        assertNotNull(response);
        BookDetailsResponse bookDetailsResponse = response.getBody();
        assertNotNull(bookDetailsResponse);
        assertEquals(bookSaved.getBookName(), bookDetailsResponse.getBookName());
    }

    @Test
    void getBookByIds() {
        String[] defaultIds = new String[]{"1"};
        ResponseEntity<BookDetailsResponse[]> response = getResponseEntityFromAPI(urlUtil.getBookByBookIdsUrl(defaultIds),
                null, HttpMethod.GET, BookDetailsResponse[].class);
        BookDetailsResponse[] books = response.getBody();
        assertNotNull(books);
        assertTrue(books.length > 0);
        assertEquals(books.length, h2Repository.findAll().size());
        assertEquals(books[0].getBookName(), getDefaultBookRequest().getBookName());
    }

    @Test
    void testSaveBookNegative() {
        ResponseEntity<CustomExceptionBean> response = getResponseEntityFromAPI(urlUtil.getSaveBookUrl(),
                getDefaultBookRequest(), HttpMethod.POST, CustomExceptionBean.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).getMessage()
                .contains(ExceptionMessage.BOOK_ALREADY_EXISTS.getExceptionMessage()));
    }

    private void saveDefaultBook() {
        ResponseEntity<BookDetailsResponse> response = getResponseEntityFromAPI(urlUtil.getSaveBookUrl(),
                getDefaultBookRequest(), HttpMethod.POST, BookDetailsResponse.class);
        assertNotNull(response);
        BookDetailsResponse bookDetails = response.getBody();
        assertNotNull(bookDetails);
    }

    private BookRequest getDefaultBookRequest() {
        BookRequest request = new BookRequest();
        request.setBookName("Book For Testing");
        request.setBookPrice(1000);
        request.setAuthorName("Test Author");
        request.setAuthorEmail("test.author@gmail.com");
        return request;
    }

    private <T> ResponseEntity<T> getResponseEntityFromAPI(String url, BookRequest payload, HttpMethod method, Class<T> type) {
        return HttpMethod.POST.equals(method) ?
                testRestTemplate.postForEntity(url, payload, type) :
                testRestTemplate.getForEntity(url, type);
    }

}