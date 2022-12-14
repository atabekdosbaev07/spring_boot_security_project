package com.project.service;

import com.project.dto.CourseRequest;
import com.project.dto.CourseResponse;
import com.project.entity.Company;
import com.project.entity.Course;
import com.project.repository.CompanyRepository;
import com.project.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

        private final CompanyRepository companyRepository;
        private final CourseRepository courseRepository;

    public Course create(CourseRequest request, Long companyId){
        Company company = companyRepository.findById(companyId).get();
        request.setCompany(company);
        Course course = mapToEntity(request);
       return courseRepository.save(course);
       // return companyResponse(course);
    }

    public CourseResponse update(Long id, CourseRequest request) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()){
            System.out.println("Company id not found");
    }
    mapToUpdateCompany(course.get(),request);
        return companyResponse(courseRepository.save(course.get()));
    }

    public CourseResponse getById(Long id) {
        Course company = courseRepository.findById(id).get();
        return companyResponse(company);
    }

    public void deleteById(Long id) {
        Course company = courseRepository.findById(id).get();
        courseRepository.delete(company);
    }

    public Course mapToEntity(CourseRequest request) {
        Optional<Company> company = companyRepository.findById(request.getCompany_id());
        Course course = new Course();
        BeanUtils.copyProperties(request,course);
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());
        course.setCompany_id(request.getCompany_id());
        course.setCompany(company.get());
        return course;
    }

    public void mapToUpdateCompany(Course course, CourseRequest request) {
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());

    }

    public CourseResponse companyResponse(Course company) {
        return CourseResponse.builder()
                .id(company.getId())
                .company_id(company.getCompany_id())
                .courseName(company.getCourseName())
                .duration(company.getDuration())
                .build();
    }

    public List<Course> getAllCourse() {

        return courseRepository.findAll();
    }
}
