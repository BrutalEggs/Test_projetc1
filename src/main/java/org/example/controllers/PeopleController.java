package org.example.controllers;

import jakarta.validation.Valid;
import org.example.DAO.PersonDAO;
import org.example.model.Person;
import org.example.unit.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * created by dmitry.fateev
 * 07.06.2022
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String indexPeople(Model model){
        model.addAttribute("people", personDAO.indexPeople());
        return "people/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person,bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.showPerson(id));
        model.addAttribute("books", personDAO.showBooks(id));
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.showPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
      //  personValidator.validate(person,bindingResult);

        if (bindingResult.hasErrors())
            return "people/edit";
        personDAO.updatePerson(person,id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

}
