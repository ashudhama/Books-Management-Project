/**
 * 
 */
package com.management.books.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.management.books.dao.BookDao;
import com.management.books.dao.CommentDao;
import com.management.books.dto.BookCommentDto;
import com.management.books.helper.BookHelper;
import com.management.books.model.Book;
import com.management.books.model.Comment;

@Controller
public class HomeController {

	@Autowired
	BookDao bookDao;
	
	@Autowired
	CommentDao commentDao;
	
	@RequestMapping(value = "/books")
	public ModelAndView  getBooks(ModelAndView model){
		List<BookCommentDto> bookCommentDto = new ArrayList<BookCommentDto>();
		List<Book> books = bookDao.findAll();
		for(Book book : books){
			List<Comment> comment = commentDao.findByBookId(book.getId());
			BookCommentDto commentDto = new BookCommentDto();
			BookHelper helper = new BookHelper();
			commentDto = helper.getData(book, comment);
			bookCommentDto.add(commentDto);
		}
		model.addObject("bookCommentDto",bookCommentDto);
		model.setViewName("books");
		return model;
	}
	
	@RequestMapping(value = "/addBook")
	public ModelAndView addBook(ModelAndView model){
		model.setViewName("addBook");		
		return model;
	}
	
	@RequestMapping(value = "/edit")
	public ModelAndView getBook(ModelAndView model, HttpServletRequest request){
		String id = request.getParameter("id");
		Integer bid = Integer.parseInt(id);
		Book book = new Book();
		book = bookDao.findById(bid).orElse(new Book());
		List<Comment> comment = commentDao.findByBookId(book.getId());		
		model.setViewName("editBook");
		model.addObject("book",book);
		model.addObject("comments",comment);
		return model;
	}
	
	@RequestMapping(value = "/saveBook" ,method=RequestMethod.POST)
	public ModelAndView saveBook(Book book,ModelAndView model){	
		bookDao.save(book);	
		model.setViewName("/books");
		List<BookCommentDto> bookCommentDto = new ArrayList<BookCommentDto>();
		List<Book> books = bookDao.findAll();
		for(Book b : books){
			List<Comment> comment = commentDao.findByBookId(b.getId());
			BookCommentDto commentDto = new BookCommentDto();
			BookHelper helper = new BookHelper();
			commentDto = helper.getData(b, comment);
			bookCommentDto.add(commentDto);
		}		
		model.addObject("bookCommentDto",bookCommentDto);
		return model = new ModelAndView("redirect:/books");
	}
	
	@RequestMapping(value = "/delete")
	public ModelAndView deleteBook( ModelAndView model,HttpServletRequest request){	
		String id = request.getParameter("id");
		Integer bid = Integer.parseInt(id);
		Book book =  bookDao.getOne(bid);		
		bookDao.delete(book);
		model.setViewName("books");
		List<BookCommentDto> bookCommentDto = new ArrayList<BookCommentDto>();
		List<Book> books = bookDao.findAll();
		for(Book b : books){
			List<Comment> comment = commentDao.findByBookId(b.getId());
			BookCommentDto commentDto = new BookCommentDto();
			BookHelper helper = new BookHelper();
			commentDto = helper.getData(b, comment);
			bookCommentDto.add(commentDto);
		}		
		model.addObject("bookCommentDto",bookCommentDto);
		return model = new ModelAndView("redirect:/books");
	}
	
	@RequestMapping(value = "/addComment" ,method=RequestMethod.POST)
	public ModelAndView addComment(@RequestParam("comment") String comment,@RequestParam("bookId") int bookId,ModelAndView model){	
		Comment co = new Comment();
		co.setDate(new Date());
		co.setComment(comment);
		co.setBookId(bookId);
		commentDao.save(co) ;
		Book book = new Book();
		book = bookDao.findById(co.getBookId()).orElse(new Book());
		List<Comment> c = commentDao.findByBookId(book.getId());		
		model.setViewName("editBook");
		model.addObject("book",book);
		model.addObject("comments",c);
		return model;
	}
	
	@RequestMapping(value = "/updateBook" ,method=RequestMethod.POST)
	public ModelAndView updateBook(Book book, ModelAndView model ){		
		Book book1 = bookDao.getOne(book.getId());
		book1.setAuthor(book.getAuthor());
		book1.setIsbn(book.getIsbn());
		book1.setTitle(book.getTitle());
		bookDao.save(book1);
		return model = new ModelAndView("redirect:/books");
	}
}
