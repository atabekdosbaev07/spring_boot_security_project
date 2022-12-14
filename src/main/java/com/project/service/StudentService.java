package com.project.service;

import com.project.dto.StudentRequest;
import com.project.dto.StudentResponse;
import com.project.dto.StudentResponseView;
import com.project.entity.Group;
import com.project.entity.Student;
import com.project.repository.GroupRepository;
import com.project.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    public Student create(StudentRequest request, Long groupId){
        Group group = groupRepository.findById(groupId).get();
        request.setGroup(group);
        Student student = mapToEntity(request);
        return studentRepository.save(student);
         //studentResponse(student);
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()){
            System.out.println("Student id not found");
    }
    mapToUpdateStudent(student.get(),request);
        return studentResponse(studentRepository.save(student.get()));
    }

    public StudentResponse getById(Long id) {
        Student student = studentRepository.findById(id).get();
        return studentResponse(student);
    }

    public void deleteById(Long id) {
        Student student = studentRepository.findById(id).get();
        studentRepository.delete(student);
    }

    public Student mapToEntity(StudentRequest request) {
        Optional<Group> group = groupRepository.findById(request.getGroupId());
        Student student = new Student();
        BeanUtils.copyProperties(request,student);
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setStudyFormat(request.getStudyFormat());
        student.setEmail(request.getEmail());
        student.setCreated(LocalDate.now());
        student.setGroupId(student.getGroupId());
        student.setGroup(group.get());
        return student;
    }

    public void mapToUpdateStudent(Student student, StudentRequest request) {
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setStudyFormat(request.getStudyFormat());
    }

    public StudentResponse studentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .studyFormat(student.getStudyFormat())
                .email(student.getEmail())
                .created(student.getCreated())
                .build();
    }

    public List<Student> getAllStudents() {

        return studentRepository.findAll();
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
            return studentRepository.getByPagination(pageable);
        } else {
            //String text = name == null ? " " : name;
            return studentRepository.searchAndPagination(name.toUpperCase(), pageable);
        }
    }

    public Long count(Long co){
        return studentRepository.countByIdT(co);
    }

    public Long countGroups(Long n1){
        return studentRepository.countGroup(n1);
    }
}

