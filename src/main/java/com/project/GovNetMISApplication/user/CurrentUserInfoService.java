package com.project.GovNetMISApplication.user;
import com.project.GovNetMISApplication.Departments.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrentUserInfoService {
    @Autowired
    private  UserService usersSer;


    public Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Users){
            Users user=(Users) authentication.getPrincipal();
            return user;
        }
        return null;
    }
    public Long getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Users){
            Users user=(Users) authentication.getPrincipal();
            return user.getId();
        }
        return null;
    }
    public String getCurrentUserFirstName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Users){
            Users user = (Users) authentication.getPrincipal();
            return user.getFirstName();
        }
        return null;
    }
    public String getCurrentUserLastName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Users){
            Users user = (Users) authentication.getPrincipal();
            return user.getLastName();
        }
        return null;
    }
    public String getCurrentUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Users){
            Users user = (Users) authentication.getPrincipal();
            return user.getEmail();
        }
        return null;
    }
    public List<String> getCurrentUserRoles(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Users){
            Users user = (Users) authentication.getPrincipal();
            return user.getRole().stream().map(roles ->
                    roles.getRoleName()).collect(Collectors.toList());
        }
        return null;
    }

    public Boolean CanUserAccessSecrets(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Users){
            
            Users user = (Users) authentication.getPrincipal();
            List<String> allroles = user.getRole().stream().map(roles ->
                    roles.getRoleName()).collect(Collectors.toList());
            if (allroles.contains("view_secret")) {
                return true;
            }
            else{
                return false;
            }
        }
        return null;
    }

    public List<Long> getCurrentUserDepartmentIds(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Users){
            Users user = (Users) authentication.getPrincipal();
            return user.getDepartment().stream().map(department ->
                    department.getDepId()).collect(Collectors.toList());
        }
        return null;
    }
    public List<Department> getCurrentUserDepartments(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Users){
            Users user = (Users) authentication.getPrincipal();
            List<Department> departmentList = usersSer.getDepartmentsByUser(user.getId());
            return departmentList;
        }
        return null;
    }

    public boolean hasSecretAccess(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null){
           return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_secret_view"));
        }
        return false;
    }
}
