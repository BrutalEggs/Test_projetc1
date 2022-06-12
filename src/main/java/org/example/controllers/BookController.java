package org.example.controllers;

import jakarta.validation.Valid;
import org.example.DAO.BookDAO;
import org.example.DAO.PersonDAO;
import org.example.model.Book;
import org.example.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * created by dmitry.fateev
 * 07.06.2022
 */

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDAO.indexBook());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "people/new";
        bookDAO.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDAO.showBook(id));
      //  Person owner = bookDAO.getOwner(id);
        if (bookDAO.getOwner(id) != null)
            model.addAttribute("owner", bookDAO.getOwner(id));
        else
            model.addAttribute("people", personDAO.indexPeople());
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("person") @Valid Book book,
                         BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return "people/edit";
        bookDAO.updateBook(book, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDAO.assign(id, person);
        return "redirect:/books/" + id;
    }

}
