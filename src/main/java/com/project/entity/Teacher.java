package com.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="teacher_gen" )
    @SequenceGenerator(name = "teacher_gen",
            sequenceName = "teacher_seq",
            allocationSize = 1)
    private Long id;
    private String firstName;
    private String email;
    private String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @Transient
    private Long courseId;
}
