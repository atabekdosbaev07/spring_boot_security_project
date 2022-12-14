package com.project.dto;

import com.project.entity.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupRequest {

    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;

    private List<Course> course;

    private Long courseId;
}
