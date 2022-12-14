package com.project.controller;

import com.project.dto.CourseRequest;
import com.project.dto.CourseResponse;
import com.project.entity.Course;
import com.project.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/course")
@PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
@Tag(name = "Course API",
        description = "User with role admin, editor can add, update, delete or get all course")
public class CourseController {

        private final CourseService courseService;

    @PostMapping
    @Operation(summary = "create course", description = "we can create course")
    public Course create(@RequestBody CourseRequest request){
        return courseService.create(request,request.getCompany_id());
    }

    @PutMapping("{id}")
    @Operation(summary = "update course", description = "we can update course")
    public CourseResponse update(@PathVariable Long id, @RequestBody CourseRequest request){
        return courseService.update(id, request);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete course", description = "we can delete course")
    public void deleteById(@PathVariable Long id){
        courseService.deleteById(id);
    }

    @GetMapping("{id}")
    @Operation(summary = "get course by id", description = "we can get course by id")
    public CourseResponse getById(@PathVariable Long id){
        return courseService.getById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "get all courses", description = "we can get all courses")
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();
    }
}
