package com.project.dto;

import com.project.entity.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequest {

    private String courseName;
    private int duration;
    private Company company;
    private Long company_id;
}
