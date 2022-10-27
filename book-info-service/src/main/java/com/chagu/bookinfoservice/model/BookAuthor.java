package com.chagu.bookinfoservice.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "author_name", unique = true)
    @NotEmpty(message = "Author name cannot be empty.")
    private String authorName;

    @Column(name = "author_email", unique = true)
    @Email(message = "Please provide valid email for author.")
    private String authorEmail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookAuthor", orphanRemoval = true)
    private final List<Book> books = new ArrayList<>();

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public List<Book> getBooks() {
        return books;
    }
}
