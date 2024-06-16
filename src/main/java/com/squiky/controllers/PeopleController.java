package com.squiky.controllers;

import com.squiky.models.Person;
import com.squiky.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "/people/allPeople";
    }

    @GetMapping("/new")
    public String newPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "/people/createNew";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("person") Person person) {
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable int id) {
        model.addAttribute("person", peopleService.findById(id));
        model.addAttribute("books", peopleService.getBooks(id));
        return "/people/personById";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable int id, Model model) {
        model.addAttribute("person", peopleService.findById(id));
        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable int id,
                       @ModelAttribute("updatedPerson") Person updatedPerson) {
        peopleService.update(id, updatedPerson);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        peopleService.deleteById(id);
        return "redirect:/people";
    }
}
