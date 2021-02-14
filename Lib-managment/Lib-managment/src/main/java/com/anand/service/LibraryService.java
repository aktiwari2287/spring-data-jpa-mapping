package com.anand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anand.exception.ResourceNotFoundException;
import com.anand.model.Library;
import com.anand.repository.LibraryRepository;

@Service
public class LibraryService {
	@Autowired
	private LibraryRepository libraryRepository;

	public List<Library> findAll() {
		return libraryRepository.findAll();
	}

	public Library addLibrary(Library library) {
		return libraryRepository.save(library);
	}

	public void delete(Long lib_id) {
		libraryRepository.deleteById(lib_id);
	}

	public Library updateLibrary(Long lib_id, Library library) {
		Library lib = libraryRepository.findById(lib_id)
				.orElseThrow(()->new ResourceNotFoundException("Library id "+ lib_id + "Not found"));
		lib.setName(library.getName());
		return libraryRepository.save(lib);
	}
}
