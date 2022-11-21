package com.peaksoft.spring_boot.repository;

import com.peaksoft.spring_boot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository1 extends JpaRepository<Student, Long> {
}