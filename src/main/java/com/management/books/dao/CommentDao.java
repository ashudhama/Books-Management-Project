package com.management.books.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.books.model.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer>{

	List<Comment> findByBookId(int bookid);
}
