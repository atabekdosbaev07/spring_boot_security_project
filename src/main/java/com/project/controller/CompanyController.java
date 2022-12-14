package com.project.controller;

import com.project.dto.CompanyRequest;
import com.project.dto.CompanyResponse;
import com.project.entity.Company;
import com.project.service.CompanyService;
import com.project.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/company")
@PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
@Tag(name = "Company API",
        description = "User with role admin, editor can add, update, delete or get all company")
public class CompanyController {

    private final CompanyService companyService;

    private final StudentService studentService;

    @PostMapping
    @Operation(summary = "create company", description = "we can create company")
    public CompanyResponse create(@RequestBody CompanyRequest request){
        return companyService.create(request);
    }

    @PutMapping("{id}")
    @Operation(summary = "update company", description = "we can update company")
    public CompanyResponse update(@PathVariable Long id, @RequestBody CompanyRequest request){
        return companyService.update(id, request);
    }

    @GetMapping("/count")
    public Long countById(Long id){
        return studentService.count(id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete company", description = "we can delete company")
    public void deleteById(@PathVariable Long id){
        companyService.deleteById(id);
    }

    @GetMapping("{id}")
    @Operation(summary = "get company by id", description = "we can get company by id")
    public CompanyResponse getById(@PathVariable Long id){
        return companyService.getById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "get all companies", description = "we can get all companies")
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();
    }
}
