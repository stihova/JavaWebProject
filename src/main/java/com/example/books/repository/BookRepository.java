/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.books.repository;

import com.example.books.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author User6ycee
 */
public interface BookRepository extends JpaRepository<Book, Long>{
    @Query(value="select * from tbl_books b where b.book_id like %:keyword% or "
            + "b.book_name like %:keyword% "
            + "or b.author like %:keyword% "
            + "or b.genre like %:keyword% ", nativeQuery=true)
    List<Book> findByKeyword(@Param("keyword") String keyword);
}
