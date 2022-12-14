package com.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Group {

    //id, groupName, dateOfStart, dateOfFinish
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="group_gen" )
    @SequenceGenerator(name = "group_gen",
            sequenceName = "group_seq",
            allocationSize = 1)
    private Long id;
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinTable(
            name = "groups_courses",
            joinColumns = @JoinColumn(name = "groups_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id",referencedColumnName = "id")
    )
    private List<Course> course;

    @Transient
    private Long courseId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "group",cascade = {CascadeType.REFRESH,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private List<Student> students;
}
