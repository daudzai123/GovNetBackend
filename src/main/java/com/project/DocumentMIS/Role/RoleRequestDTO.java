package com.project.DocumentMIS.Role;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDTO {
    private String roleName;
    private String description;
    private List<String> permissions;
}
