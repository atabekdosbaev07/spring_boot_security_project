package com.project.dto;

import com.project.entity.Group;
import com.project.enumm.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentRequest {

    private String firstName;
    private String lastName;
    private String email;

    private StudyFormat studyFormat;

    private Group group;

    private Long groupId;

}
