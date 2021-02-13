package com.anand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.anand.model.Book;
import com.anand.model.Library;
import com.anand.repository.BookRepository;
import com.anand.repository.LibraryRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	public Book addBook(Long lib_id, Book book) {
		Library library = libraryRepository.findById(lib_id).orElse(new Library(lib_id, "Default"));
		book.setLibrary(library);
		return bookRepository.save(book);
	}
	
	public void delete(Long book_id) {
		bookRepository.deleteById(book_id);
	}
	
	
	public Book updateBook(Long book_id, Book book) {
		Book b = bookRepository.findById(book_id).orElse(null);
		if(b!=null) {
			b.setAvailable(book.getAvailable());
			b.setCover(book.getCover());
			b.setIsbn(book.getIsbn());
			b.setPages(book.getPages());
			b.setPublisher(book.getPublisher());
			b.setTitle(book.getTitle());
			return bookRepository.saveAndFlush(b);
		}
		else {
			return new Book();
		}
		
	}
}
