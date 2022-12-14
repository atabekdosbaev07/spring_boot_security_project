package com.project.dto;

import com.project.entity.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequest {

    private String firstName;
    private String email;
    private String lastName;

    private Course course;
    private Long courseId;
}
