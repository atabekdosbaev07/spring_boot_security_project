package com.peaksoft.spring_boot.controller;

import com.peaksoft.spring_boot.dto.StudentRequest;
import com.peaksoft.spring_boot.dto.StudentResponse;
import com.peaksoft.spring_boot.dto.StudentResponseView;
import com.peaksoft.spring_boot.entity.Student;
import com.peaksoft.spring_boot.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/students")
@PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
@Tag(name = "Student API",
        description = "User with role admin, editor can add, update, delete or get all students")
public class StudentController {


    private final StudentService studentService;

    @PostMapping
    @Operation(summary = "create student", description = "we can create student")
    public StudentResponse create(@RequestBody StudentRequest request){
        return studentService.create(request);
    }

    @PutMapping("{id}")
    @Operation(summary = "update student", description = "we can update student")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request){
        return studentService.update(id, request);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete student", description = "we can delete student")
    public void deleteById(@PathVariable Long id){
        studentService.deleteById(id);
    }

    @GetMapping("{id}")
    @Operation(summary = "get student by id", description = "we can get student by id")
    public StudentResponse getById(@PathVariable Long id){
        return studentService.getById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "get all students", description = "we can get all students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping
    public StudentResponseView getAll(@RequestParam(name = "text", required = false) String text,
                                      @RequestParam int page,
                                      @RequestParam int size){
        return studentService.getAllStudentsPagination(text, page, size);

    }

}
