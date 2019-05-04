package com.management.books.helper;

import java.util.List;

import com.management.books.dto.BookCommentDto;
import com.management.books.model.Book;
import com.management.books.model.Comment;

public class BookHelper {

	public BookCommentDto getData(Book book, List<Comment> comment){
		BookCommentDto dto = new BookCommentDto();
		dto.setId(book.getId());
		dto.setAuthor(book.getAuthor());
		dto.setIsbn(book.getIsbn());
		dto.setTitle(book.getTitle());
		dto.setComments(comment);
		dto.setCommentCount(comment.size());
		return dto; 
	}
}
