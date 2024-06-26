package com.squiky.controllers;

import com.squiky.models.Person;
import com.squiky.services.PeopleService;
import com.squiky.util.validators.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "/people/allPeople";
    }

    @GetMapping("/new")
    public String newPage(Model model) {
        model.addAttribute("person", new Person());
        return "/people/createNew";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) return "/people/createNew";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable int id) {
        Person person = peopleService.findById(id);

        model.addAttribute("person", person);
        model.addAttribute("books", person.getBookList());

        return "/people/personById";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable int id, Model model) {
        model.addAttribute("person", peopleService.findById(id));
        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String makeUpdate(@PathVariable int id,
                       @ModelAttribute("person") @Valid Person updatedPerson,
                       BindingResult bindingResult) {
        personValidator.validate(updatedPerson, bindingResult);

        if (bindingResult.hasErrors()) return "/people/edit";

        peopleService.update(id, updatedPerson);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        peopleService.deleteById(id);
        return "redirect:/people";
    }
}
