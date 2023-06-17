package com.rusan.library_a_p_i.repos;

import com.rusan.library_a_p_i.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
