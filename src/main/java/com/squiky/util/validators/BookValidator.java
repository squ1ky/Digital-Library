package com.squiky.util.validators;

import com.squiky.models.Book;
import com.squiky.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class BookValidator implements Validator {

    private final BooksService booksService;

    @Autowired
    public BookValidator(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        Book bookInStorage = booksService.findByTitleAndAuthor(
                book.getTitle(),
                book.getAuthor()
        );

        if (bookInStorage != null) {
            errors.rejectValue("titleAndAuthor", "", "This book is already taken");
        }
    }
}
