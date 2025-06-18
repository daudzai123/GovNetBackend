package com.project.DocumentMIS.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.project.DocumentMIS.ActivitiesLog.ActivityLog;
import com.project.DocumentMIS.ActivitiesLog.ActivityLogService;
import com.project.DocumentMIS.Departments.Department;
import com.project.DocumentMIS.Departments.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DocumentMIS.ExceptionHandlingFiles.MyNotFoundException;
import com.project.DocumentMIS.Permission.Permission;
import com.project.DocumentMIS.Permission.PermissionRepository;


@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository; 

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ActivityLogService activityLogService;
    @Autowired
    private DepartmentRepository departmentRepository;

   
    public void createRole(RoleRequestDTO roleRequest) {
        Roles role = new Roles();
        role.setRoleName(roleRequest.getRoleName());
        role.setDescription(roleRequest.getDescription());

        List<Permission> permissions = permissionRepository.findAllBypermissionNameIn(roleRequest.getPermissions());
        role.setPermissions(permissions);
        Roles save = roleRepository.save(role);
        List<Department> all = departmentRepository.findAll();
        Map<String,Object> details=new HashMap<>();
        details.put("added roleName ",save.getRoleName());
        List<String> permissionNames = save.getPermissions().stream().map(permission -> {
            String permissionName = permission.getPermissionName();
            return permissionName;
        }).collect(Collectors.toList());
        details.put("permissions related to new added role ",permissionNames);
        ActivityLog activityLog=new ActivityLog();
        String content = activityLog.MapToString(details);
        activityLogService.logsActivity("Role",role.getId(),"Created",all,content);
    }

    public void assignPermissionsToRole(RolePermissionAssignmentRequestDTO assignmentRequest) {
        Roles role = roleRepository.findById(assignmentRequest.getId()).orElseThrow(()-> new MyNotFoundException("Role not found"));
        List<String> previousPermissionNames = role.getPermissions().stream().map(permission -> {
            String permissionName = permission.getPermissionName();
            return permissionName;
        }).collect(Collectors.toList());
        if (role != null) {
        // Delete previously assigned permissions of the role
        role.setPermissions(new ArrayList<>()); // Assuming 'setPermissions' is the method to set permissions in Roles entity
        // Fetch permissions from the database
        List<Permission> permissions = permissionRepository.findAllBypermissionNameIn(assignmentRequest.getPermissions());

        // Assign new permissions to the role
        role.setPermissions(permissions);
        role.setRoleName(assignmentRequest.getRoleName());
        role.setDescription(assignmentRequest.getDescription());
            List<String> updatedPermissionNames = permissions.stream().map(permission -> {
                String permissionName = permission.getPermissionName();
                return permissionName;
            }).collect(Collectors.toList());

            // Save the updated role
        roleRepository.save(role);
            List<Department> all = departmentRepository.findAll();
            Map<String,Object> details=new HashMap<>();
            details.put("updated Role",role.getRoleName());
            details.put("previous permissions ",previousPermissionNames);
            details.put("updated permissions ",updatedPermissionNames);
            ActivityLog activityLog=new ActivityLog();
            String content = activityLog.MapToString(details);
            activityLogService.logsActivity("Role",role.getId(),"Assigned permissions to role",all,content);
        }
    }
}
