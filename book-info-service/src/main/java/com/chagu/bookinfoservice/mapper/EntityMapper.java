package com.chagu.bookinfoservice.mapper;

import com.chagu.bookinfoservice.dto.response.AuthorDetailsResponse;
import com.chagu.bookinfoservice.dto.response.BookDetailsResponse;
import com.chagu.bookinfoservice.mapper.config.ModelMapperProvider;
import com.chagu.bookinfoservice.model.Book;
import com.chagu.bookinfoservice.model.BookAuthor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    private final ModelMapperProvider modelMapperProvider;

    public EntityMapper(ModelMapperProvider modelMapperProvider) {
        this.modelMapperProvider = modelMapperProvider;
    }

    public BookDetailsResponse mapBookToBookDetailsResponse(Book book) {
        final ModelMapper mapper = modelMapperProvider.getMapperForBookDetails();
        return mapper.map(book, BookDetailsResponse.class);
    }

    public AuthorDetailsResponse mapAuthorToAuthorDetailsResponse(BookAuthor author) {
        final ModelMapper mapper = modelMapperProvider.getMapperForAuthorDetails();
        return mapper.map(author, AuthorDetailsResponse.class);
    }
}
