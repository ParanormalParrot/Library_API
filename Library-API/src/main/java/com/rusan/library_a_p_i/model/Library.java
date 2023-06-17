package com.rusan.library_a_p_i.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Library {

    private Long id;

    @Size(max = 255)
    private String name;

}
