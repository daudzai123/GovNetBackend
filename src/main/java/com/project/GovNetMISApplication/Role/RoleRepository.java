package com.project.GovNetMISApplication.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// @Query("SELECT r FROM Roles r WHERE r.roleName = :roleName")
// Optional<Roles> findByroleName(@Param("roleName") String roleName);

// public Optional<Roles> findByRoleName(String roleName);
public interface RoleRepository extends JpaRepository<Roles, Long> {
    public Optional<Roles> findByroleName(String roleName);
}
