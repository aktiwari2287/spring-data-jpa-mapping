package com.anand.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.anand.exception.ResourceNotFoundException;
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
		return libraryRepository.findById(lib_id).map((lib) -> {
			book.setLibrary(lib);
			return bookRepository.save(book);
		}).orElseThrow(() -> new ResourceNotFoundException("LibId " + lib_id + " not found"));
	}

	public void delete(Long book_id) {
		bookRepository.deleteById(book_id);
	}

	public Book updateBook(Long book_id, Book book) {
		return bookRepository.findById(book_id).map(b -> {
			b.setAvailable(book.getAvailable());
			b.setCover(book.getCover());
			b.setIsbn(book.getIsbn());
			b.setPages(book.getPages());
			b.setPublisher(book.getPublisher());
			b.setTitle(book.getTitle());
			return bookRepository.save(b);
		}).orElseThrow(() -> new ResourceNotFoundException("Book with book_id " + book_id + " not found!"));

	}
	
	public List<Book> getAllBooksForALibrary(Long lib_id) {
		return bookRepository.getAllBooksForALibrary(lib_id);
	}
}
