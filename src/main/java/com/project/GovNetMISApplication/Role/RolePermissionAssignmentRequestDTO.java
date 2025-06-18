package com.project.GovNetMISApplication.Role;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePermissionAssignmentRequestDTO {
    private Long id;
    private String roleName;
    private String description;
    private List<String> permissions;
}
