package com.project.GovNetMISApplication.user;
import com.project.GovNetMISApplication.user.Enum.userTypes;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class UserDTO {
    private Long id;
    @NotBlank
    private String firstName;
    private String lastName;
    @NotBlank
    private String position;
    private String phoneNo;
    private boolean activate = true;
    @Email
    @Column(unique = true)
    private String email;
    @Size(min = 6,message = "Password must be grater than 6 character!")
    private String password;
    private userTypes userType;
    private List<Long> departmentIds;
    private List<String> roleName;
    public <R> UserDTO(String fullName, String email, R collect) {

    }
    public List<String> getRoleName() {
        return roleName != null ? roleName : new ArrayList<>();
    }
    public UserDTO() {
    }



}
