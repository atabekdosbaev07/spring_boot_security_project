package com.project.dto;

import com.project.entity.Course;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeacherResponse {

    private Long id;
    private String firstName;
    private String email;
    private String lastName;

    private Course course;

    private Long courseId;
}
