package com.squiky.controllers;

import com.squiky.models.Book;
import com.squiky.services.BooksService;
import com.squiky.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public String findAll(Model model,
                          @RequestParam(required = false, name = "page") Integer page,
                          @RequestParam(required = false, name = "books_per_page") Integer booksPerPage,
                          @RequestParam(required = false, name = "sort_by_year") Boolean sortByYear) {
        if (page != null && booksPerPage != null) {
            if (sortByYear != null) {
                if (sortByYear.equals(true)) {
                    model.addAttribute("books", booksService.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfPublishing"))).getContent());
                    return "/books/allBooks";
                }
            }

            model.addAttribute("books", booksService.findAll(PageRequest.of(page, booksPerPage)).getContent());
            return "/books/allBooks";
        }

        if (sortByYear != null) {
            if (sortByYear.equals(true)) {
                model.addAttribute("books", booksService.findAll(Sort.by("yearOfPublishing")));
                return "/books/allBooks";
            }
        }

        model.addAttribute("books", booksService.findAll());
        return "/books/allBooks";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "/books/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("title") String title) {
        model.addAttribute("books", booksService.findByTitleStartingWith(title));
        return "/books/search";
    }

    @GetMapping("/new")
    public String createPage(Model model) {
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
    public String makeUpdate(@PathVariable int id,
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
    public String makeAvailable(@PathVariable("id") int bookId) {
        booksService.makeFree(bookId);
        return "redirect:/books";
    }
}
