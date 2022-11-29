package com.peaksoft.spring_boot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentResponseView {

    private List<StudentResponse> responses;
}
