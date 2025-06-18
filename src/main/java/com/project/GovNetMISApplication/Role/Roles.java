package com.project.GovNetMISApplication.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.project.GovNetMISApplication.Permission.Permission;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Roles implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    private String description;
    @Override
    public String getAuthority() {
        return roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "role_permissions", 
      joinColumns = @JoinColumn(name = "role_id"), 
      inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;
}
