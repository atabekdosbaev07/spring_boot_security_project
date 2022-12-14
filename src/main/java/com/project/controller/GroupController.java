package com.project.controller;

import com.project.dto.GroupRequest;
import com.project.dto.GroupResponse;
import com.project.entity.Group;
import com.project.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/group")
@PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
@Tag(name = "Group API",
        description = "User with role admin, editor can add, update, delete or get all groups")
public class GroupController {
        private final GroupService groupService;

    @PostMapping
    @Operation(summary = "create group", description = "we can create group")
    public Group create(@RequestBody GroupRequest request){
        return groupService.create(request,request.getCourseId());
    }

    @PutMapping("{id}")
    @Operation(summary = "update group", description = "we can update group")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest request){
        return groupService.update(id, request);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete group", description = "we can delete group")
    public void deleteById(@PathVariable Long id){
        groupService.deleteById(id);
    }

    @GetMapping("{id}")
    @Operation(summary = "get group by id", description = "we can get group by id")
    public GroupResponse getById(@PathVariable Long id){
        return groupService.getById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "get all groups", description = "we can get all groups")
    public List<Group> getAllGroup(){
        return groupService.getAllGroup();
    }
}
