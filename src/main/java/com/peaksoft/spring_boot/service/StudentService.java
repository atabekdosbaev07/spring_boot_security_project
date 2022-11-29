package com.peaksoft.spring_boot.service;

import com.peaksoft.spring_boot.dto.StudentRequest;
import com.peaksoft.spring_boot.dto.StudentResponse;
import com.peaksoft.spring_boot.dto.StudentResponseView;
import com.peaksoft.spring_boot.entity.Student;
import com.peaksoft.spring_boot.repository.StudentRepository1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository1 studentRepository1;

    public StudentResponse create(StudentRequest request){
        Student student = mapToEntity(request);
        studentRepository1.save(student);
        return studentResponse(student);
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Optional<Student> student = studentRepository1.findById(id);
        if (student.isEmpty()){
            System.out.println("Student id not found");
    }
    mapToUpdateStudent(student.get(),request);
        return studentResponse(studentRepository1.save(student.get()));
    }

    public StudentResponse getById(Long id) {
        Student student = studentRepository1.findById(id).get();
        return studentResponse(student);
    }

    public void deleteById(Long id) {
        Student student = studentRepository1.findById(id).get();
        studentRepository1.delete(student);
    }

    public Student mapToEntity(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setCreated(LocalDate.now());
        student.setDeleted(student.getDeleted());
        student.setIsActive(student.getIsActive());
        return student;
    }

    public void mapToUpdateStudent(Student student, StudentRequest request) {
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
    }

    public StudentResponse studentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .email(student.getEmail())
                .created(student.getCreated())
                .isActive(student.getIsActive())
                .build();
    }

    public List<Student> getAllStudents() {

        return studentRepository1.findAll();
    }


    public StudentResponseView getAllStudentsPagination(String text, int page, int size){
        StudentResponseView responseView = new StudentResponseView();
        PageRequest pageable =  PageRequest.of(page -1, size);
        responseView.setResponses(view(search(text, pageable)));
        return responseView;
    }

    public List<StudentResponse> view(List<Student> students){
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student:students) {
            responses.add(studentResponse(student));
        }
        return  responses;
    }

    private List<Student> search(String name, PageRequest pageable) {
        if (name == null) {
            return studentRepository1.getByPagination(pageable);
        } else {
            //String text = name == null ? " " : name;
            return studentRepository1.searchAndPagination(name.toUpperCase(), pageable);
        }
    }
}

