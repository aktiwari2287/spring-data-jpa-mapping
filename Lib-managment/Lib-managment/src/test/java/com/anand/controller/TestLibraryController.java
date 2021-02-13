package com.anand.controller;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
		Mockito.when(service.findAll()).thenReturn(libraries);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/libraries/all").accept(MediaType.APPLICATION_JSON)).andReturn();
		String expected = "[{\"lib_id\":1,\"name\":\"Test Library\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}
