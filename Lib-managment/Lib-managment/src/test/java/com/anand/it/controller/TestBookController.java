package com.anand.it.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.anand.LibManagmentApplication;

@SpringBootTest(
		webEnvironment=WebEnvironment.RANDOM_PORT,
		classes=LibManagmentApplication.class
	)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class TestBookController {

	@Autowired
	MockMvc mockMvc;
	
	MvcResult result=null;
	@BeforeEach
    void init() throws Exception {
		String request = "{\"name\":\"HTML\"}";
		result = mockMvc.perform(post("/libraries/add")
		           .contentType(MediaType.APPLICATION_JSON)
		           .content(request) 
		           .accept(MediaType.APPLICATION_JSON))
		           .andReturn();
		
		result = mockMvc.perform(get("/libraries/all")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		JSONArray arr = new JSONArray(result.getResponse().getContentAsString());
		JSONObject lib = arr.getJSONObject(0);
		
		String addBookRequest="{\"isbn\":\"123\",\"title\":\"html\",\"cover\":\"html 5\",\"publisher\":\"Anand kumar\",\"pages\":\"500\",\"available\":\"105\"}";
		mockMvc.perform(post("/books/add/"+lib.getInt("lib_id"))
		           .contentType(MediaType.APPLICATION_JSON)
		           .content(addBookRequest) 
		           .accept(MediaType.APPLICATION_JSON)).andReturn();
    }

	@AfterEach
	void tearDown() throws Exception {
		 result = mockMvc.perform(get("/libraries/all")
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();
			JSONArray arr = new JSONArray(result.getResponse().getContentAsString());
			result = mockMvc.perform(get("/books/all/")
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();
			
			JSONArray books = new JSONArray(result.getResponse().getContentAsString());
			for(int i=0;i<books.length();i++) {
				mockMvc.perform(delete("/books/remove/"+books.getJSONObject(i).getInt("book_id"))
						.accept(MediaType.APPLICATION_JSON))
						.andReturn();
			}	
		for(int i=0;i<arr.length();i++) {
			JSONObject lib = arr.getJSONObject(i);
			mockMvc.perform(delete("/libraries/remove/"+lib.getInt("lib_id"))
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();

		}
	}
	
	@Test
	public void testAddBook() throws Exception {
		result = mockMvc.perform(get("/libraries/all")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		JSONArray arr = new JSONArray(result.getResponse().getContentAsString());
		JSONObject lib = arr.getJSONObject(0);
		String addBookRequest="{\"isbn\":\"123\",\"title\":\"html\",\"cover\":\"html 5\",\"publisher\":\"Anand kumar\",\"pages\":\"500\",\"available\":\"105\"}";
		
		mockMvc.perform(post("/books/add/"+lib.getInt("lib_id"))
		           .contentType(MediaType.APPLICATION_JSON)
		           .content(addBookRequest) 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk())
		           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		           .andExpect(jsonPath("$.isbn").value("123"))
		           .andExpect(jsonPath("$.title").value("html"))
		           .andExpect(jsonPath("$.cover").value("html 5"))
		           .andExpect(jsonPath("$.publisher").value("Anand kumar"))
		           .andExpect(jsonPath("$.pages").value("500"));
	}
	
	
	@Test
	public void getAllBooks() throws Exception {
		result = mockMvc.perform(get("/books/all")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		JSONArray arr = new JSONArray(result.getResponse().getContentAsString());
		Assertions.assertTrue(arr.length()>0);
	}
	
	@Test
	public void testDeleteBook() throws Exception {
		result = mockMvc.perform(get("/books/all")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		JSONArray arr = new JSONArray(result.getResponse().getContentAsString());
		int beforeLength = arr.length();
		
		mockMvc.perform(delete("/books/remove/"+arr.getJSONObject(0).getInt("book_id"))
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		result = mockMvc.perform(get("/books/all")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		arr = new JSONArray(result.getResponse().getContentAsString());
		int afterLength = arr.length();
		Assertions.assertTrue(beforeLength>afterLength);
	}
	
	@Test
	public void testUpdateBook() throws Exception {
		result = mockMvc.perform(get("/books/all")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		JSONArray arr = new JSONArray(result.getResponse().getContentAsString());
		JSONObject bookObj = arr.getJSONObject(0);
		String updateBookRequest="{\"isbn\":\"12345\",\"title\":\"html-updated\",\"cover\":\"html5-updated\",\"publisher\":\"Anand kumar\",\"pages\":\"500\",\"available\":\"105\"}";
		mockMvc.perform(put("/books/update/"+bookObj.getInt("book_id"))
		           .contentType(MediaType.APPLICATION_JSON)
		           .content(updateBookRequest) 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk())
		           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		           .andExpect(jsonPath("$.isbn").value("12345"))
		           .andExpect(jsonPath("$.title").value("html-updated"))
		           .andExpect(jsonPath("$.cover").value("html5-updated"))
		           .andExpect(jsonPath("$.publisher").value("Anand kumar"))
		           .andExpect(jsonPath("$.pages").value("500"));
	}
}
