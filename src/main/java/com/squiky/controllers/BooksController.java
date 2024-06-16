package com.squiky.controllers;

import com.squiky.models.Book;
import com.squiky.services.BooksService;
import com.squiky.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService,
                           PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "/books/allBooks";
    }

    @GetMapping("/new")
    public String newBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "/books/createNew";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "/books/createNew";

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String find(@PathVariable int id, Model model) {
        Book book = booksService.findById(id);

        model.addAttribute("book", book);
        model.addAttribute("people", peopleService.findAll());

        if (book.getPerson() != null) {
            model.addAttribute("curPerson", peopleService.findById(book.getPerson().getId()));
        }

        return "/books/bookById";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable int id,
                         @ModelAttribute("book") @Valid Book updatedBook,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "/books/edit";

        booksService.update(id, updatedBook);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int bookId,
                         @RequestParam("personId") int personId) {
        booksService.assignToPerson(bookId, personId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/make-free")
    public String makeFree(@PathVariable("id") int bookId) {
        booksService.makeFree(bookId);
        return "redirect:/books";
    }
}
