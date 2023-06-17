package com.rusan.library_a_p_i.repos;

import com.rusan.library_a_p_i.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LibraryRepository extends JpaRepository<Library, Long> {
}
