package com.rusan.library_a_p_i.service;

import com.rusan.library_a_p_i.domain.Library;
import com.rusan.library_a_p_i.domain.Reader;
import com.rusan.library_a_p_i.model.Book;
import com.rusan.library_a_p_i.repos.BookRepository;
import com.rusan.library_a_p_i.repos.LibraryRepository;
import com.rusan.library_a_p_i.repos.ReaderRepository;
import com.rusan.library_a_p_i.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;
    private final ReaderRepository readerRepository;

    public BookService(final BookRepository bookRepository,
            final LibraryRepository libraryRepository, final ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
        this.readerRepository = readerRepository;
    }

    public List<Book> findAll() {
        final List<com.rusan.library_a_p_i.domain.Book> books = bookRepository.findAll(Sort.by("id"));
        return books.stream()
                .map(book -> mapToDTO(book, new Book()))
                .toList();
    }

    public Book get(final Long id) {
        return bookRepository.findById(id)
                .map(book -> mapToDTO(book, new Book()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final Book bookDTO) {
        final com.rusan.library_a_p_i.domain.Book book = new com.rusan.library_a_p_i.domain.Book();
        mapToEntity(bookDTO, book);
        return bookRepository.save(book).getId();
    }

    public void update(final Long id, final Book bookDTO) {
        final com.rusan.library_a_p_i.domain.Book book = bookRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(bookDTO, book);
        bookRepository.save(book);
    }

    public void delete(final Long id) {
        bookRepository.deleteById(id);
    }

    private Book mapToDTO(final com.rusan.library_a_p_i.domain.Book book, final Book bookDTO) {
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setStatus(book.getStatus());
        bookDTO.setLibrary(book.getLibrary() == null ? null : book.getLibrary().getId());
        bookDTO.setReader(book.getReader() == null ? null : book.getReader().getId());
        return bookDTO;
    }

    private com.rusan.library_a_p_i.domain.Book mapToEntity(final Book bookDTO, final com.rusan.library_a_p_i.domain.Book book) {
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setStatus(bookDTO.getStatus());
        final Library library = bookDTO.getLibrary() == null ? null : libraryRepository.findById(bookDTO.getLibrary())
                .orElseThrow(() -> new NotFoundException("library not found"));
        book.setLibrary(library);
        final Reader reader = bookDTO.getReader() == null ? null : readerRepository.findById(bookDTO.getReader())
                .orElseThrow(() -> new NotFoundException("reader not found"));
        book.setReader(reader);
        return book;
    }

}
