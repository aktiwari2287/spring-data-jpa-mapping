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
import com.anand.repository.LibraryRepository;

@RestController
@RequestMapping("/libraries")
public class LibraryController {
	
	@Autowired
	private LibraryRepository libraryRepository;

	
	@GetMapping("/all") 
	public List<Library> getLibraries(){
		return libraryRepository.findAll();
	}
	
	@PostMapping("/add")
	public List<Library> addLibrary(@RequestBody Library library) {
		libraryRepository.save(library);
		return libraryRepository.findAll();
	}
	
	@DeleteMapping("/remove/{lib_id}")
	public void delete(@PathVariable("lib_id") Long lib_id) {
		libraryRepository.deleteById(lib_id);
	}
	
	@PutMapping("/update/{lib_id}")
	public List<Library> updateLibrary(@PathVariable("lib_id") Long lib_id, @RequestBody Library library) {
		Library lib = libraryRepository.findById(lib_id).orElse(library);
		lib.setName(library.getName());
		libraryRepository.save(lib);
		return libraryRepository.findAll();
	}
}
