package com.chagu.bookinfoservice.mapper.config;

import com.chagu.bookinfoservice.dto.response.AuthorDetailsResponse;
import com.chagu.bookinfoservice.dto.response.BookDetailsResponse;
import com.chagu.bookinfoservice.model.Book;
import com.chagu.bookinfoservice.model.BookAuthor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperProvider {

    public ModelMapper getMapperForBookDetails() {
        final ModelMapper mapper = getModelMapper();
        final TypeMap<Book, BookDetailsResponse> propertyMapper = mapper.createTypeMap(Book.class, BookDetailsResponse.class);
        propertyMapper.addMapping(b -> b.getBookAuthor().getAuthorName(), BookDetailsResponse::setBookAuthor);
        return mapper;
    }

    public ModelMapper getMapperForAuthorDetails() {
        final Converter<List<Book>, List<String>> bookNameConverter = ctx -> ctx.getSource().stream()
                .map(Book::getBookName).collect(Collectors.toList());
        final ModelMapper mapper = getModelMapper();
        mapper.typeMap(BookAuthor.class, AuthorDetailsResponse.class)
                .addMappings(m -> m.using(bookNameConverter).map(BookAuthor::getBooks, AuthorDetailsResponse::setBooks));
        return mapper;
    }

    private ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.getConfiguration().setSkipNullEnabled(true);
        return mapper;
    }
}
