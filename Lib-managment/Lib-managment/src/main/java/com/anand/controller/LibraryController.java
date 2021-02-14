package com.anand.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anand.model.Library;
import com.anand.service.LibraryService;

@RestController
@RequestMapping("/libraries")
public class LibraryController {
	
	@Autowired
	private LibraryService libraryService;

	
	@GetMapping("/all") 
	public List<Library> getLibraries(){
		return libraryService.findAll();
	}
	
	@PostMapping("/add")
	public Library addLibrary(@RequestBody Library library) {
		return libraryService.addLibrary(library);
	}
	
	@DeleteMapping("/remove/{lib_id}")
	public void delete(@PathVariable("lib_id") Long lib_id) {
		libraryService.delete(lib_id);
	}
	
	@PutMapping("/update/{lib_id}")
	public Library updateLibrary(@PathVariable("lib_id") Long lib_id, @RequestBody Library library) {
		return libraryService.updateLibrary(lib_id, library);
	}
}
