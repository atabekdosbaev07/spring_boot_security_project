package com.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.enumm.StudyFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {
//id, firstName, email, lastName, studyFormat(Enum)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="student_gen" )
    @SequenceGenerator(name = "student_gen",
            sequenceName = "student_seq",
            allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    @CreatedDate
    private LocalDate created;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @Transient
    private Long groupId;
}

