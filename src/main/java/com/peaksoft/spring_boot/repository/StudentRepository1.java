package com.peaksoft.spring_boot.repository;

import com.peaksoft.spring_boot.entity.Student;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository1 extends JpaRepository<Student, Long> {

    @Query("select s from Student s")
    List<Student>getByPagination(PageRequest pageRequest);

    @Query("SELECT s FROM Student s WHERE UPPER(s.name) LIKE concat(' % ', :text, ' %')" +
            "OR UPPER(s.surname) LIKE concat(' %', :text, '%') OR UPPER(s.email) LIKE concat('%', :text, '%')")
    List<Student> searchAndPagination(@Param("text") String text, PageRequest pageable);
}