package com.anand.junit.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.anand.controller.BookController;
import com.anand.model.Book;
import com.anand.service.BookService;

@WebMvcTest(controllers = BookController.class)
public class TestBookController {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	BookService service;

	@Test
	public void testGetBooks() throws Exception {
		List<Book> books = new ArrayList();
		books.add(new Book("1234", "java", "black book", "xyz", 120, 200));
		when(service.getAllBooks()).thenReturn(books);
		MvcResult result = mockMvc
				.perform(get("/books/all").accept(MediaType.APPLICATION_JSON)).andReturn();
		String expected = "[{\"isbn\":\"1234\",\"title\":\"java\",\"cover\":\"black book\",\"publisher\":\"xyz\",\"pages\":120,\"available\":200}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		verify(service).getAllBooks();
	}
	
	@Test
	public void testAddBook() throws Exception {
		Book book = new Book(2L, "1234", "java", "black book", "xyz", 120, 200);
		when(service.addBook(Mockito.anyLong(), Mockito.any())).thenReturn(book);
		String request = "{\"isbn\":\"1234\",\"title\":\"java\",\"cover\":\"black book\",\"publisher\":\"xyz\",\"pages\":120,\"available\":200}";
		MvcResult result =  mockMvc.perform(post("/books/add/1")
					           .contentType(MediaType.APPLICATION_JSON)
					           .content(request) 
					           .accept(MediaType.APPLICATION_JSON))
					           .andReturn();
		JSONObject bookObj = new JSONObject(result.getResponse().getContentAsString());
		Assertions.assertEquals(2L, bookObj.getLong("book_id"));
	}
	
	@Test
	public void testUpdateBook() throws Exception {
		Book book = new Book(2L, "1234", "java-updated", "black book", "xyz", 120, 200);
		when(service.updateBook(Mockito.anyLong(), Mockito.any())).thenReturn(book);
		String request = "{\"isbn\":\"1234\",\"title\":\"java-updated\",\"cover\":\"black book\",\"publisher\":\"xyz\",\"pages\":120,\"available\":200}";
		MvcResult result =  mockMvc.perform(put("/books/update/2")
					           .contentType(MediaType.APPLICATION_JSON)
					           .content(request) 
					           .accept(MediaType.APPLICATION_JSON))
					           .andReturn();
		JSONObject bookObj = new JSONObject(result.getResponse().getContentAsString());
		Assertions.assertEquals(2L, bookObj.getLong("book_id"));
		Assertions.assertEquals("java-updated", bookObj.getString("title"));
	}
}
