package com.chagu.bookinfoservice.repository;

import com.chagu.bookinfoservice.model.Book;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private Session getSession(){
        return entityManager.unwrap(Session.class);
    }

    public List<Book> getBookListByIds(List<Integer> bookIds){
        MultiIdentifierLoadAccess<Book> multiIdentifierLoadAccess = getSession().byMultipleIds(Book.class);
        return multiIdentifierLoadAccess.withBatchSize(10).multiLoad(bookIds);
    }
}
