package com.project.dto;

import com.project.entity.Group;
import com.project.enumm.StudyFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Builder
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate created;

    private StudyFormat studyFormat;

    private Group group;

    private Long groupId;
}
