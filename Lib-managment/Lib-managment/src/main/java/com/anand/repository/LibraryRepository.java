package com.anand.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anand.model.Library;

public interface LibraryRepository extends JpaRepository<Library, Long>{

}
