package com.anand.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anand.model.Book;
import com.anand.model.Library;
import com.anand.repository.BookRepository;
import com.anand.repository.LibraryRepository;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@GetMapping("/all")
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	@PostMapping("/add/{lib_id}")
	public Book addBook(@PathVariable("lib_id") long lib_id, @RequestBody Book book) {
		Library library = libraryRepository.findById(lib_id).orElse(new Library(lib_id, "Default"));
		book.setLibrary(library);
		return bookRepository.save(book);
	}
	
	@DeleteMapping("/remove/{book_id}")
	public void delete(@PathVariable("book_id") Long book_id) {
		bookRepository.deleteById(book_id);
	}
	
}
