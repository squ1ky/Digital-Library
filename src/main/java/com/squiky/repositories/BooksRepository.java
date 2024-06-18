package com.squiky.repositories;

import com.squiky.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitleAndAuthor(String title, String author);
    List<Book> findByTitleStartingWith(String title);
}
