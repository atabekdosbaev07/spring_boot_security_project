package com.peaksoft.spring_boot.controller;

import com.peaksoft.spring_boot.dto.StudentRequest;
import com.peaksoft.spring_boot.dto.StudentResponse;
import com.peaksoft.spring_boot.entity.Student;
import com.peaksoft.spring_boot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/students")
public class StudentController {


    private final StudentService studentService;

    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request){
        return studentService.create(request);
    }

    @PutMapping("{id}")
    public StudentResponse update(@PathVariable Long id, @RequestBody StudentRequest request){
        return studentService.update(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        studentService.deleteById(id);
    }

    @GetMapping("{id}")
    public StudentResponse getById(@PathVariable Long id){
        return studentService.getById(id);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

}
