package com.management.books.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.books.model.Book;

public interface BookDao extends JpaRepository<Book, Integer>{

	
}
