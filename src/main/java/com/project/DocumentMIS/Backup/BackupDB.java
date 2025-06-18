package com.project.DocumentMIS.Backup;

import java.time.LocalDateTime;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.DocumentMIS.user.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@EnableAspectJAutoProxy
public class BackupDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String backupPath;
    private LocalDateTime created_at;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(
        value = { "lastName","position","activate","department",
        "profilePath","email","password","downloadURL","otpCode",
        "otpExpiration","userType","role","getAuthorities" },
        allowSetters = true
    )
    private Users creator;
    
}
