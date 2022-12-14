package com.project.repository;

import com.project.entity.Student;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s")
    List<Student>getByPagination(PageRequest pageRequest);

    @Query("SELECT s FROM Student s WHERE UPPER(s.firstName) LIKE concat(' % ', :text, ' %')" +
            "OR UPPER(s.lastName) LIKE concat(' %', :text, '%') OR UPPER(s.email) LIKE concat('%', :text, '%')")
    List<Student> searchAndPagination(@Param("text") String text, PageRequest pageable);

    @Query("SELECT COUNT(s.id) FROM Student s ")
    Long countByIdT(Long id);

    @Query("SELECT COUNT(s.group.id) FROM Student s WHERE s.group.id=?1 ")
    Long countGroup(Long id);

}