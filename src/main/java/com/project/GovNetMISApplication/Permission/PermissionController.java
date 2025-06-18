package com.project.GovNetMISApplication.Permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.project.GovNetMISApplication.ActivitiesLog.ActivityLog;
import com.project.GovNetMISApplication.ActivitiesLog.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/per")
public class PermissionController {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ActivityLogService activityLogService;



    @PostMapping("/add")
    public ResponseEntity<String> addPermission(@RequestBody Permission permission){
        Permission save = permissionRepository.save(permission);
        Map<String,Object> details=new HashMap<>();
        details.put("added Permission name ",save.getPermissionName());
        ActivityLog activityLog=new ActivityLog();
        String content = activityLog.MapToString(details);
        activityLogService.logsActivity("Permission",save.getId(),"created",null,content);
        return ResponseEntity.ok("Permission added successfully");
    }
    @GetMapping("/all")
    public List<Permission> getAllRoles() {
        List<Permission> perlist = permissionRepository.findAll();
        return perlist;
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Permission>> getById(@PathVariable Long id) {
        Optional<Permission> permission = permissionRepository.findById(id);
        return ResponseEntity.ok().body(permission);
    }
}
