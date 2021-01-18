/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.books.controller;

import com.example.books.model.Book;
import com.example.books.service.BookService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User6ycee
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/")
    public String viewBookPage(Model model, String keyword) {
        List<Book> bookList = bookService.listAll();
        if(keyword != null) {
            model.addAttribute("getBooks", bookService.findByKeyword(keyword));
        }
        else {
            model.addAttribute("getBooks", bookList);
        }
        return "home";
    }

    @RequestMapping("/add_book")
    public String viewAddBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "add_book";
    }

    @RequestMapping(value = "/save_book", method = RequestMethod.POST)
    public String addNewBook(Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add_book";
        }
        else {
            bookService.create(book);
            return "redirect:/";
        }
    }

    @RequestMapping("/edit/{bookId}")
    public ModelAndView viewEditBookForm(@PathVariable(name = "bookId") long id) {
        ModelAndView mav = new ModelAndView("update_book");
        Book book = bookService.updateid(id);
        mav.addObject("book", book);
        return mav;
    }

    @RequestMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable(name = "bookId") long id) {
        bookService.delete(id);
        return "redirect:/";
    }
}
