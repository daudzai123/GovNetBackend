package com.project.GovNetMISApplication.Permission;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    
    public Permission findBypermissionName(String permissionName);
    
    public List<Permission> findAllBypermissionNameIn(List<String> permissionNames);
}
