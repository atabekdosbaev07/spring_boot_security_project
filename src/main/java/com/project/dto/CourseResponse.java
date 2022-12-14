package com.project.dto;

import com.project.entity.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseResponse {

    private Long id;
    private String courseName;
    private int duration;
    private Company company;
    private Long company_id;
}
