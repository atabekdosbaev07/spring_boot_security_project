package com.project.service;

import com.project.dto.GroupRequest;
import com.project.dto.GroupResponse;
import com.project.entity.Course;
import com.project.entity.Group;
import com.project.repository.CourseRepository;
import com.project.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
        private final GroupRepository groupRepository;

        private final CourseRepository courseRepository;
        /*(CourseRequest request,Long companyId){

        Company company = companyRepository.findById(companyId).get();
        request.setCompany(company);
        Course course = mapToEntity(request);
       return courseRepository.save(course);*/

    public Group create(GroupRequest request, Long courseId){
        Course course = courseRepository.findById(courseId).get();
        request.setCourse(Collections.singletonList(course));
        Group group = mapToEntity(request);
        return  groupRepository.save(group);
      //  return groupResponse(group);
    }

    public GroupResponse update(Long id, GroupRequest request) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isEmpty()){
            System.out.println("Group id not found");
    }
    mapToUpdateGroup(group.get(),request);
        return groupResponse(groupRepository.save(group.get()));
    }

    public GroupResponse getById(Long id) {
        Group group = groupRepository.findById(id).get();
        return groupResponse(group);
    }

    public void deleteById(Long id) {
        Group group = groupRepository.findById(id).get();
        groupRepository.delete(group);
    }

    public Group mapToEntity(GroupRequest request) {
        Optional<Course> course = courseRepository.findById(request.getCourseId());
        Group group = new Group();
        BeanUtils.copyProperties(request,group);
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        group.setCourseId(request.getCourseId());
        group.setCourse(Collections.singletonList(course.get()));

        return group;
    }

    public void mapToUpdateGroup(Group group, GroupRequest request) {
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());


    }

    public GroupResponse groupResponse(Group group) {
        return GroupResponse.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .dateOfStart(group.getDateOfStart())
                .dateOfFinish(group.getDateOfFinish())
                .courseId(group.getCourseId())
                .build();
    }

    public List<Group> getAllGroup() {

        return groupRepository.findAll();
    }



    public List<GroupResponse> view(List<Group> groups){
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group:groups) {
            responses.add(groupResponse(group));
        }
        return  responses;
    }
}
