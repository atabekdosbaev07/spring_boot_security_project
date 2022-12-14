package com.project.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class CompanyResponse {

    private Long id;
    private String companyName;
    private String locatedCountry;

    private LocalDate created;
}
