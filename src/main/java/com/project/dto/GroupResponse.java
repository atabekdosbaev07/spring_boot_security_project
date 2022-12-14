package com.project.dto;

import com.project.entity.Course;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GroupResponse {

    private Long id;
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;

    private List<Course> course;

    private Long courseId;
}
