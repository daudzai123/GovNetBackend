package com.project.GovNetMISApplication.ActivitiesLog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.GovNetMISApplication.Departments.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String entityName;
    private String action;
    @Column(length = 6000)
    private String content;
    private LocalDateTime timestamp;
    private Long recordId;
    private String userName;
    private String departmentName;
    @ManyToMany
    List<Department> departmentList = new ArrayList<>();
    public String MapToString(Map<String, Object> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.content = objectMapper.writeValueAsString(map);
        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
            this.content = "[]";
        }
        return content;
    }
}
