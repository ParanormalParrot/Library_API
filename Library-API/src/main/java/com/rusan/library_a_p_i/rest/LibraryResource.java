package com.rusan.library_a_p_i.rest;

import com.rusan.library_a_p_i.model.Library;
import com.rusan.library_a_p_i.service.LibraryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/librarys", produces = MediaType.APPLICATION_JSON_VALUE)
public class LibraryResource {

    private final LibraryService libraryService;

    public LibraryResource(final LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<List<Library>> getAllLibrarys() {
        return ResponseEntity.ok(libraryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibrary(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(libraryService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createLibrary(@RequestBody @Valid final Library library) {
        final Long createdId = libraryService.create(library);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLibrary(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final Library library) {
        libraryService.update(id, library);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteLibrary(@PathVariable(name = "id") final Long id) {
        libraryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
