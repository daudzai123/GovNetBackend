package com.project.GovNetMISApplication.user;
import lombok.Data;

@Data
public class UserDepartmentDTO {
    private Long depId;
    private String depName;
    private String description;
    private Long usercount;
}