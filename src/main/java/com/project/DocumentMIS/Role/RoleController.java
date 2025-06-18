package com.project.DocumentMIS.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepo;

    @PostMapping("/add")
    public void addRole(@RequestBody RoleRequestDTO role) {
        roleService.createRole(role);
    }

    @GetMapping("/all")
    public Page<Roles> getAllRoles(Pageable pageable) {
        Page<Roles> rolelist=roleRepo.findAll(pageable);
        return rolelist;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Roles>> getById(@PathVariable Long id) {
        Optional<Roles> byId = roleRepo.findById(id);
        return ResponseEntity.ok().body(byId);
    }
    @PostMapping("/assign-permissions")
    public ResponseEntity<String> assignPermissionsToRole(
            @Valid @RequestBody RolePermissionAssignmentRequestDTO assignmentRequest) {
        try {
            System.out.println("==============In Try Block=============" + assignmentRequest);
            roleService.assignPermissionsToRole(assignmentRequest);
            return ResponseEntity.ok("Permissions assigned successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error assigning permissions to role " + e.getMessage());
        }
    }
}