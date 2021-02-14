package com.anand.junit.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.anand.controller.LibraryController;
import com.anand.model.Library;
import com.anand.service.LibraryService;

@WebMvcTest(controllers = LibraryController.class)
public class TestLibraryController {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	LibraryService service;

	@Test
	public void testGetLibraries() throws Exception {
		List<Library> libraries = new ArrayList();
		libraries.add(new Library(1L, "Test Library"));
		when(service.findAll()).thenReturn(libraries);

		MvcResult result = mockMvc
				.perform(get("/libraries/all").accept(MediaType.APPLICATION_JSON)).andReturn();
		String expected = "[{\"lib_id\":1,\"name\":\"Test Library\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		verify(service).findAll();
	}
	
	@Test
	public void testAddLibrary() throws Exception {
		Library addedLibrary = new Library(1L, "Test Library");
		when(service.addLibrary(Mockito.any())).thenReturn(addedLibrary);
		
		String request = "{\"name\":\"Test Library\"}";
		MvcResult result =  mockMvc.perform(post("/libraries/add")
					           .contentType(MediaType.APPLICATION_JSON)
					           .content(request) 
					           .accept(MediaType.APPLICATION_JSON))
					           .andReturn();
		String expected = "{\"lib_id\":1,\"name\":\"Test Library\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testUpdateLibrary() throws Exception {
		Library updatedLibrary = new Library(1L, "Test Library-updated");
		when(service.updateLibrary(Mockito.anyLong(), Mockito.any())).thenReturn(updatedLibrary);
		String request = "{\"name\":\"Test Library-updated\"}";
		MvcResult result =  mockMvc.perform(put("/libraries/update/1")
					           .contentType(MediaType.APPLICATION_JSON)
					           .content(request) 
					           .accept(MediaType.APPLICATION_JSON))
					           .andReturn();
		String expected = "{\"lib_id\":1,\"name\":\"Test Library-updated\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	
}
