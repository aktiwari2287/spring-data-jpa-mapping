package com.anand.it.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.anand.LibManagmentApplication;

@SpringBootTest(
			webEnvironment=WebEnvironment.RANDOM_PORT,
			classes=LibManagmentApplication.class
		)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class TestLibraryController {

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
    }

	@AfterEach
	void tearDown() throws Exception {
		 result = mockMvc.perform(get("/libraries/all")
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();
			JSONArray arr = new JSONArray(result.getResponse().getContentAsString());
		for(int i=0;i<arr.length();i++) {
			JSONObject lib = arr.getJSONObject(i);
			mockMvc.perform(delete("/libraries/remove/"+lib.getInt("lib_id"))
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();

		}
	}
	
	@Test
	public void testAddLibrary() throws Exception {
		JSONAssert.assertEquals("{\"name\":\"HTML\"}", result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testGetLibraries() throws Exception {
		
		MvcResult result = mockMvc.perform(get("/libraries/all")
											.accept(MediaType.APPLICATION_JSON))
											.andReturn();
		JSONArray arr = new JSONArray(result.getResponse().getContentAsString());
		Assertions.assertNotNull(arr);
		Assertions.assertTrue(arr.length()>0);
	}
	
	
	@Test
	public void testDeleteLibrary() throws Exception {
		MvcResult result = mockMvc.perform(get("/libraries/all")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		JSONArray arr = new JSONArray(result.getResponse().getContentAsString());
		JSONObject lib = arr.getJSONObject(0);
		int beforeLength = arr.length();
		result = mockMvc.perform(delete("/libraries/remove/"+lib.getInt("lib_id"))
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		result = mockMvc.perform(get("/libraries/all")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		arr = new JSONArray(result.getResponse().getContentAsString());
		int afterLength=arr.length();
		Assertions.assertNotEquals(beforeLength, afterLength);
	}
	
	@Test
	public void testUpdateLibrary() throws Exception {
		String updateRequest = "{\"name\":\"HTML - updated\"}";
		 mockMvc.perform(put("/libraries/update/1")
		           .contentType(MediaType.APPLICATION_JSON)
		           .content(updateRequest) 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk())
		           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		           .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("HTML - updated"));
		 
	}
	
	
}
