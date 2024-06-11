package com.squiky.controllers;

import com.squiky.models.Book;
import com.squiky.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
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
        model.addAttribute("book", booksService.findById(id));
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
}
