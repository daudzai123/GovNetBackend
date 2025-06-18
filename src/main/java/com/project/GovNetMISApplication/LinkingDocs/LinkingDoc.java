package com.project.GovNetMISApplication.LinkingDocs;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.user.Users;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LinkingDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    private Long first;
    private Long second;
    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "first")
    // @JsonIgnoreProperties(
    //     value = { "creationDate", "updateDate", "docName","sendDocList","department", "reference","userId","appendantDocsList" },
    //     allowSetters = true
    // )
    // private Document firstDoc;
    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "second")
    // @JsonIgnoreProperties(
    //     value = { "creationDate", "updateDate", "docName","sendDocList","department", "reference","userId","appendantDocsList" },
    //     allowSetters = true
    // )
    // private Document secondDoc;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(
        value = { "lastName","position","activate","department",
        "profilePath","email","password","downloadURL","otpCode",
        "otpExpiration","userType","role","getAuthorities" },
        allowSetters = true
    )
    private Users user;
    @ManyToOne
    @JsonIgnoreProperties(value = { "parentDepartmentId", "description", "users" }, allowSetters = true)
    private Department department;
}
