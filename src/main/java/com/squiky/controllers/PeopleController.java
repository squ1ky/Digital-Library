package com.squiky.controllers;

import com.squiky.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String newPerson() {
        return "/people/createNew";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable int id) {
        model.addAttribute("person", peopleService.findById(id));
        return "/people/personById";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id) {
        return "/people/edit";
    }
}
