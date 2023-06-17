package com.rusan.library_a_p_i.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Book {

    private Long id;

    @Size(max = 255)
    private String title;

    @Size(max = 255)
    private String author;

    private BookStatus status;

    private Long library;

    private Long reader;

}
