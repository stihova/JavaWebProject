/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.books.service;

import com.example.books.model.Book;
import com.example.books.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User6ycee
 */
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    public List<Book > listAll() {
        return bookRepo.findAll();
    }

    public void create(Book book) {
        bookRepo.save(book);
    }

    public Book updateid(Long id) {
        return bookRepo.findById(id).get();
    }

    public void delete(Long id) {
        bookRepo.deleteById(id);
    }
    
    public List<Book> findByKeyword(String keyword) {
        return bookRepo.findByKeyword(keyword);
    }
}
