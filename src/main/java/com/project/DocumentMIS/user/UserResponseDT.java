package com.project.DocumentMIS.user;

import java.util.ArrayList;
import java.util.List;

import com.project.DocumentMIS.Departments.DepwithUserDto;
import com.project.DocumentMIS.user.Enum.userTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDT{
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private boolean activate;
    private String email;
    private String phoneNo;
    private userTypes userType;
    private List<DepwithUserDto> departments;
    private List<String> roleName;
    private String profilePath;
    private String downloadURL;

    public List<String> getRoleName() {
        return roleName != null ? roleName : new ArrayList<>();
    }

}
