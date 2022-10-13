package com.chagu.bookcatalogservice.client;

import com.chagu.bookcatalogservice.constant.BookInfoUrl;
import com.chagu.bookcatalogservice.constant.ServiceName;
import com.chagu.bookcatalogservice.model.BookInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class BookInfoClient {

    private final WebClient webClient;

    public BookInfoClient(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public List<BookInfo> findAllBookInfo() {
        return getResponse(BookInfoUrl.getUrlForFindAllBookInfo()).bodyToFlux(BookInfo.class).collectList().block();
    }

    public BookInfo findByBookId(String bookId) {
        return getResponse(BookInfoUrl.getUrlForFindBookInfoByBookId(bookId)).bodyToMono(BookInfo.class).block();
    }

    public List<BookInfo> findAllBookInfoByIds(String[] bookIds) {
        return getResponse(BookInfoUrl.getUrlForFindAllBookInfoByIds(bookIds))
                .bodyToFlux(BookInfo.class).collectList().block();
    }

    public BookInfo findByBookName(String bookName) {
        return getResponse(BookInfoUrl.getUrlForFindBookInfoByBookName(bookName)).bodyToMono(BookInfo.class).block();
    }

    private WebClient.ResponseSpec getResponse(String path) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .host(ServiceName.BOOK_INFO_SERVICE.getServiceName())
                        .path(path)
                        .build())
                .retrieve();
    }
}