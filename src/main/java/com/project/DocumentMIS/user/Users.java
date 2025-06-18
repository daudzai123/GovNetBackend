package com.project.DocumentMIS.user;

import com.project.DocumentMIS.Permission.Permission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.DocumentMIS.Departments.Department;
import com.project.DocumentMIS.Role.Roles;
import com.project.DocumentMIS.user.Enum.userTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String firstName;
    @NotBlank
    private String lastName;
    private String position;
    @NonNull
    @Email
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean activate;
    private String phoneNo;
    private String profilePath;
    @JsonIgnore
    private String downloadURL;
    @JsonIgnore
    private String otpCode; // For storing the OTP code
    @JsonIgnore
    private LocalDateTime otpExpiration; // For storing the OTP expiration time
    @Enumerated(EnumType.STRING)
    private userTypes userType;
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(
        value = { "parentDepartmentId", "description","users" },
        allowSetters = true
    )
    @JoinTable(name = "user-department",
            joinColumns = @JoinColumn(name = "user-id"),
            inverseJoinColumns = @JoinColumn(name = "department-id")
    )
    
    private List<Department> department;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user-roles",
            joinColumns = @JoinColumn(name = "user-id"),
            inverseJoinColumns = @JoinColumn(name = "role-id")
    )
    private List<Roles> role;
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
////        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(getRole().toString());
//        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
//        for (Roles role:getRole()
//             ) {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//        System.out.println("User Authorities:"+ authorities);
//        return authorities;
        //     List<SimpleGrantedAuthority> authorities = getRole().stream()
        //             .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName()))
        //             .collect(Collectors.toList());
        //     return authorities;
        // }

         List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         for (Roles role : getRole()) {
            for (Permission permission : role.getPermissions()) {
                authorities.add( new SimpleGrantedAuthority("ROLE_"+permission.getPerName()));
            }
         }
            return authorities;
        }
        @JsonIgnore
        public List<String> getAuthorityNames() {
            return getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }

    public Users() {
    }

    public Users(Users user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.position = user.getPosition();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.activate = user.isActivate();
        this.phoneNo = user.getPhoneNo();
        this.profilePath = user.getProfilePath();
        this.downloadURL = user.getDownloadURL();
        this.otpCode = user.getOtpCode();
        this.otpExpiration = user.getOtpExpiration();
        this.userType = user.getUserType();
        this.department = new ArrayList<>(user.getDepartment());
        this.role = new ArrayList<>(user.getRole());
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    
    public List<Roles> getRole(){
        return role;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}