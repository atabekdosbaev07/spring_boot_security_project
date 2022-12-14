package com.project.service;

import com.project.dto.TeacherRequest;
import com.project.dto.TeacherResponse;
import com.project.entity.Course;
import com.project.entity.Teacher;
import com.project.repository.CourseRepository;
import com.project.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

         private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public Teacher create(TeacherRequest request, Long courseId){
        Course course = courseRepository.findById(courseId).get();
        request.setCourse(course);
        Teacher teacher = mapToEntity(request);
        return teacherRepository.save(teacher);
        //return teacherResponse(teacher);
    }

    public TeacherResponse update(Long id, TeacherRequest request) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()){
            System.out.println("Teacher id not found");
    }
    mapToUpdateTeacher(teacher.get(),request);
        return teacherResponse(teacherRepository.save(teacher.get()));
    }

    public TeacherResponse getById(Long id) {
        Teacher teacher = teacherRepository.findById(id).get();
        return teacherResponse(teacher);
    }

    public void deleteById(Long id) {
        Teacher teacher = teacherRepository.findById(id).get();
        teacherRepository.delete(teacher);
    }



    public Teacher mapToEntity(TeacherRequest request) {
        Optional<Course> course = courseRepository.findById(request.getCourseId());
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(request,teacher);
        teacher.setFirstName(request.getFirstName());
        teacher.setEmail(request.getEmail());
        teacher.setLastName(request.getLastName());
        teacher.setCourseId(request.getCourseId());
        teacher.setCourse(course.get());

        return teacher;
    }

    public void mapToUpdateTeacher(Teacher teacher, TeacherRequest request) {
        teacher.setFirstName(request.getFirstName());
        teacher.setEmail(request.getEmail());
        teacher.setLastName(request.getLastName());

    }

    public TeacherResponse teacherResponse(Teacher teacher) {
        return TeacherResponse.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .email(teacher.getEmail())
                .lastName(teacher.getLastName())
                .build();
    }

    public List<Teacher> getAllTeacher() {

        return teacherRepository.findAll();
    }



    public List<TeacherResponse> view(List<Teacher> teachers){
        List<TeacherResponse> responses = new ArrayList<>();
        for (Teacher teacher:teachers) {
            responses.add(teacherResponse(teacher));
        }
        return  responses;
    }
}
