package com.project.DocumentMIS.Departments;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.DocumentMIS.ActivitiesLog.ActivityLog;
import com.project.DocumentMIS.user.Users;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depId;
    private String depName;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_dep_Id")
    @JsonIgnoreProperties(
        value = { "depName","description","parentDepartmentId","users" },
        allowSetters = true
    )
    private Department parentDepartmentId;

    @ToString.Exclude
    @ManyToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(
        value = { "lastName","position","activate","department",
        "profilePath","email","password","downloadURL","otpCode",
        "otpExpiration","userType","role","getAuthorities" },
        allowSetters = true
    )
    private List<Users> users;
    @JsonIgnore
    public List<Users> getActiveUsers() {
        return this.users.stream()
            .filter(Users::isActivate)  // Assuming 'isActivate' is the getter for the 'activate' property
            .collect(Collectors.toList());
    }

    public Department(Department department) {
        this.depId = department.getDepId();
        this.depName = department.getDepName();
        this.description = department.getDescription();
        this.parentDepartmentId = department.getParentDepartmentId();
        this.users = new ArrayList<>(department.getUsers());
    }
}