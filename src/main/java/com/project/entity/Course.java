package com.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="course_gen" )
    @SequenceGenerator(name = "course_gen",
            sequenceName = "course_seq",
            allocationSize = 1)
    private Long id;
    private String courseName;
    private int duration;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @Transient
    private Long company_id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "course", fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Group> group;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "course",cascade = {CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.EAGER)
    private Teacher teacher;
}
