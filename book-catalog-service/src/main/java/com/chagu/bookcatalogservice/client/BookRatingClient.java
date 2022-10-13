package com.chagu.bookcatalogservice.client;

import com.chagu.bookcatalogservice.constant.BookRatingUrl;
import com.chagu.bookcatalogservice.constant.ServiceName;
import com.chagu.bookcatalogservice.model.BookRating;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class BookRatingClient {

    private final WebClient webClient;

    public BookRatingClient(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public List<BookRating> findAllBookRatings() {
        return getResponse(BookRatingUrl.getUrlForFindAllBookRating())
                .bodyToFlux(BookRating.class).collectList().block();
    }

    public BookRating findByBookId(String bookId) {
        return getResponse(BookRatingUrl.getUrlForFindBookRatingByBookId(bookId))
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                .bodyToMono(BookRating.class).block();
    }

    public List<BookRating> getBookRatingsByRating(Integer rating) {
        return getResponse(BookRatingUrl.getUrlForFindAllBookRatingByRating(rating))
                .bodyToFlux(BookRating.class).collectList().block();
    }

    private WebClient.ResponseSpec getResponse(String path) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .host(ServiceName.BOOK_RATING_SERVICE.getServiceName())
                        .path(path)
                        .build())
                .retrieve();
    }

}
