package com.slk.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slk.demo.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
}
