package com.anand.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anand.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	/*@Transactional
	@Modifying
	@Query("delete from Book b where b.book_id=:book_id and b.library.lib_id=:lib_id")
	public void deleteBookById(@Param("book_id") Long book_id, @Param("lib_id") Long Lib_id);*/
}
