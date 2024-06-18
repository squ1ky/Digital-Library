package com.squiky.services;

import com.squiky.models.Book;
import com.squiky.models.Person;
import com.squiky.repositories.BooksRepository;
import com.squiky.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll().stream()
                .limit(20)
                .toList();
    }

    public Page<Book> findAll(PageRequest pageRequest) {
        return booksRepository.findAll(pageRequest);
    }

    public List<Book> findAll(Sort sort) {
        return booksRepository.findAll(sort);
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public Book findByTitleAndAuthor(String title, String author) {
        return booksRepository.findByTitleAndAuthor(title, author).orElse(null);
    }

    public List<Book> findByTitleStartingWith(String title) {
        return booksRepository.findByTitleStartingWith(title);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assignToPerson(int bookId, int personId) {
        Optional<Book> optionalBook = booksRepository.findById(bookId);
        Optional<Person> optionalPerson = peopleRepository.findById(personId);

        if (optionalBook.isPresent() && optionalPerson.isPresent()) {
            Book book = optionalBook.get();
            Person person = optionalPerson.get();

            book.setPerson(person);
            book.setTakenAt(new Date());
            person.getBookList().add(book);
            booksRepository.save(book);
        }
    }

    @Transactional
    public void makeFree(int bookId) {
        Optional<Book> optionalBook = booksRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Person owner = book.getPerson();

            owner.getBookList().remove(book);
            book.setPerson(null);
            book.setTakenAt(null);
            booksRepository.save(book);
        }
    }
}
