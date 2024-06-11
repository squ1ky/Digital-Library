package com.squiky.service;

import com.squiky.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        String SQL = "SELECT * FROM book";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Book.class));
    }

    public Book findById(int id) {
        String SQL = "SELECT * From Book WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(Book.class), id);
    }

    public void save(Book book) {
        String SQL =
                """
                INSERT INTO book(title, author, year_of_publishing)
                VALUES (?, ?, ?)
                """;
        jdbcTemplate.update(
                SQL,
                book.getTitle(),
                book.getAuthor(),
                book.getYearOfPublishing()
        );
    }

    public void update(int id, Book book) {
        String SQL =
                """
                UPDATE book
                SET title = ?, author = ?, year_of_publishing = ?
                WHERE id = ?
                """;
        jdbcTemplate.update(
                SQL,
                book.getTitle(),
                book.getAuthor(),
                book.getYearOfPublishing(),
                id
        );
    }

    public void delete(int id) {
        String SQL = "DELETE FROM book WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }
}
