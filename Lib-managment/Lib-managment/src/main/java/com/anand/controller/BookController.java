package com.anand.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anand.model.Book;
import com.anand.service.BookService;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping("/all")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@PostMapping("/add/{lib_id}")
	public Book addBook(@PathVariable("lib_id") long lib_id, @RequestBody Book book) {
		return bookService.addBook(lib_id, book);
	}
	
	@DeleteMapping("/remove/{book_id}")
	public void delete(@PathVariable("book_id") Long book_id) {
		bookService.delete(book_id);
	}
	
	
	@PutMapping("/update/{book_id}")
	public Book updateBook(@PathVariable("book_id") long book_id, @RequestBody Book book) {
		return bookService.updateBook(book_id, book);
	}
}
