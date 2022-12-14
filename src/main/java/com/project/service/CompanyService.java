package com.project.service;

import com.project.dto.CompanyRequest;
import com.project.dto.CompanyResponse;
import com.project.entity.Company;
import com.project.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

     private final CompanyRepository companyRepository;

    public CompanyResponse create(CompanyRequest request){
        Company company = mapToEntity(request);
        companyRepository.save(company);
        return companyResponse(company);
    }

    public CompanyResponse update(Long id, CompanyRequest request) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()){
            System.out.println("Company id not found");
        }
        mapToUpdateCompany(company.get(),request);
        return companyResponse(companyRepository.save(company.get()));
    }

    public CompanyResponse getById(Long id) {
        Company company = companyRepository.findById(id).get();
        return companyResponse(company);
    }

    public void deleteById(Long id) {
        Company company = companyRepository.findById(id).get();
        companyRepository.delete(company);
    }

    public Company mapToEntity(CompanyRequest request) {
        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        company.setCreated(LocalDate.now());
        return company;
    }

    public void mapToUpdateCompany(Company company, CompanyRequest request) {
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());

    }

    public CompanyResponse companyResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .locatedCountry(company.getLocatedCountry())
                .created(company.getCreated())
                .build();
    }

    public List<Company> getAllCompany() {

        return companyRepository.findAll();
    }



    public List<CompanyResponse> view(List<Company> companies){
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company:companies) {
            responses.add(companyResponse(company));
        }
        return  responses;
    }







}
