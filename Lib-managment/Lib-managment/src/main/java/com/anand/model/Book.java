package com.anand.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	@Id
	@GeneratedValue
	private long book_id;

	@Column(name = "isbn")
	private String isbn;

	@Column(name = "title")
	private String title;
	@Column(name = "cover")
	private String cover;
	@Column(name = "publisher")
	private String publisher;
	@Column(name = "pages")
	private int pages;
	@Column(name = "available")
	private int available;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fk_lib_id")
	private Library library;

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public Book(String isbn, String title, String cover, String publisher, int pages, int available) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.cover = cover;
		this.publisher = publisher;
		this.pages = pages;
		this.available = available;
	}

	public Book(Long book_id, String isbn, String title, String cover, String publisher, int pages, int available) {
		super();
		this.book_id=book_id;
		this.isbn = isbn;
		this.title = title;
		this.cover = cover;
		this.publisher = publisher;
		this.pages = pages;
		this.available = available;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public Book() {
		super();
	}

}