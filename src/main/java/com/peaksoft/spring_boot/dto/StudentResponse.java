package com.peaksoft.spring_boot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class StudentResponse {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private Boolean isActive;
    private LocalDate created;
}
