package com.project.controller;

import com.project.dto.TeacherRequest;
import com.project.dto.TeacherResponse;
import com.project.entity.Teacher;
import com.project.service.StudentService;
import com.project.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/teacher")
@PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
@Tag(name = "Teacher API",
        description = "User with role admin, editor can add, update, delete or get all teachers")
public class TeacherController {

    private final TeacherService teacherService;

    private final StudentService studentService;


    @PostMapping
    @Operation(summary = "create teacher", description = "we can create teacher")
    public Teacher create(@RequestBody TeacherRequest request){
        return teacherService.create(request,request.getCourseId());
    }

    @PutMapping("{id}")
    @Operation(summary = "update teacher", description = "we can update teacher")
    public TeacherResponse update(@PathVariable Long id, @RequestBody TeacherRequest request){
        return teacherService.update(id, request);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete teacher", description = "we can delete teacher")
    public void deleteById(@PathVariable Long id){
        teacherService.deleteById(id);
    }

    @GetMapping("{id}")
    @Operation(summary = "get teacher by id", description = "we can get teacher by id")
    public TeacherResponse getById(@PathVariable Long id){
        return teacherService.getById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "get all teachers", description = "we can get all teachers")
    public List<Teacher> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @GetMapping("/count/{id}")
    public Long countGroup(@PathVariable Long id){
        return studentService.countGroups(id);
    }
}
