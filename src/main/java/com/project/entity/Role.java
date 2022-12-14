package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "role_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_gen",
            sequenceName = "role_seq",
            allocationSize = 1)
    private Long id;
    private String roleName;


    @ManyToMany(targetEntity = User.class,
    mappedBy = "roles", cascade = {CascadeType.MERGE,
    CascadeType.REFRESH,
    CascadeType.DETACH,
    CascadeType.PERSIST})
    private List<User> users;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
