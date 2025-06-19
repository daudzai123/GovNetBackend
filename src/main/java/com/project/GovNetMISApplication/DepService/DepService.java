package com.project.GovNetMISApplication.DepService;


import com.project.GovNetMISApplication.Departments.Department;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DepService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "service_ip", nullable = false)
    private String serviceIp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
