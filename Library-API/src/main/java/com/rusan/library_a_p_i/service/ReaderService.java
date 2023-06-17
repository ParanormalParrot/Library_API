package com.rusan.library_a_p_i.service;

import com.rusan.library_a_p_i.model.Reader;
import com.rusan.library_a_p_i.repos.ReaderRepository;
import com.rusan.library_a_p_i.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderService(final ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public List<Reader> findAll() {
        final List<com.rusan.library_a_p_i.domain.Reader> readers = readerRepository.findAll(Sort.by("id"));
        return readers.stream()
                .map(reader -> mapToDTO(reader, new Reader()))
                .toList();
    }

    public Reader get(final Long id) {
        return readerRepository.findById(id)
                .map(reader -> mapToDTO(reader, new Reader()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final Reader readerDTO) {
        final com.rusan.library_a_p_i.domain.Reader reader = new com.rusan.library_a_p_i.domain.Reader();
        mapToEntity(readerDTO, reader);
        return readerRepository.save(reader).getId();
    }

    public void update(final Long id, final Reader readerDTO) {
        final com.rusan.library_a_p_i.domain.Reader reader = readerRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(readerDTO, reader);
        readerRepository.save(reader);
    }

    public void delete(final Long id) {
        readerRepository.deleteById(id);
    }

    private Reader mapToDTO(final com.rusan.library_a_p_i.domain.Reader reader, final Reader readerDTO) {
        readerDTO.setId(reader.getId());
        readerDTO.setName(reader.getName());
        return readerDTO;
    }

    private com.rusan.library_a_p_i.domain.Reader mapToEntity(final Reader readerDTO, final com.rusan.library_a_p_i.domain.Reader reader) {
        reader.setName(readerDTO.getName());
        return reader;
    }

}
