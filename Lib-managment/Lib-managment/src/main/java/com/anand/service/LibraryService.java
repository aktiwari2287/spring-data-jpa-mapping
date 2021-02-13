package com.anand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anand.model.Library;
import com.anand.repository.LibraryRepository;

@Service
public class LibraryService {
	@Autowired
	private LibraryRepository libraryRepository;

	public List<Library> findAll() {
		return libraryRepository.findAll();
	}

	public List<Library> addLibrary(Library library) {
		libraryRepository.save(library);
		return libraryRepository.findAll();
	}

	public void delete(Long lib_id) {
		libraryRepository.deleteById(lib_id);
	}

	public List<Library> updateLibrary(Long lib_id, Library library) {
		Library lib = libraryRepository.findById(lib_id).orElse(library);
		lib.setName(library.getName());
		libraryRepository.save(lib);
		return libraryRepository.findAll();
	}
}
