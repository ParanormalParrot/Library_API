package com.rusan.library_a_p_i.service;

import com.rusan.library_a_p_i.model.Library;
import com.rusan.library_a_p_i.repos.LibraryRepository;
import com.rusan.library_a_p_i.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryService(final LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<Library> findAll() {
        final List<com.rusan.library_a_p_i.domain.Library> librarys = libraryRepository.findAll(Sort.by("id"));
        return librarys.stream()
                .map(library -> mapToDTO(library, new Library()))
                .toList();
    }

    public Library get(final Long id) {
        return libraryRepository.findById(id)
                .map(library -> mapToDTO(library, new Library()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final Library libraryDTO) {
        final com.rusan.library_a_p_i.domain.Library library = new com.rusan.library_a_p_i.domain.Library();
        mapToEntity(libraryDTO, library);
        return libraryRepository.save(library).getId();
    }

    public void update(final Long id, final Library libraryDTO) {
        final com.rusan.library_a_p_i.domain.Library library = libraryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(libraryDTO, library);
        libraryRepository.save(library);
    }

    public void delete(final Long id) {
        libraryRepository.deleteById(id);
    }

    private Library mapToDTO(final com.rusan.library_a_p_i.domain.Library library, final Library libraryDTO) {
        libraryDTO.setId(library.getId());
        libraryDTO.setName(library.getName());
        return libraryDTO;
    }

    private com.rusan.library_a_p_i.domain.Library mapToEntity(final Library libraryDTO, final com.rusan.library_a_p_i.domain.Library library) {
        library.setName(libraryDTO.getName());
        return library;
    }

}
