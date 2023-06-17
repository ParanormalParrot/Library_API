package com.rusan.library_a_p_i.rest;

import com.rusan.library_a_p_i.model.Reader;
import com.rusan.library_a_p_i.service.ReaderService;
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
@RequestMapping(value = "/api/readers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReaderResource {

    private final ReaderService readerService;

    public ReaderResource(final ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public ResponseEntity<List<Reader>> getAllReaders() {
        return ResponseEntity.ok(readerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(readerService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createReader(@RequestBody @Valid final Reader reader) {
        final Long createdId = readerService.create(reader);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReader(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final Reader reader) {
        readerService.update(id, reader);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteReader(@PathVariable(name = "id") final Long id) {
        readerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
