package com.project.GovNetMISApplication.DepService;


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

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @Column(name = "service_ip", nullable = false)
    private String serviceIp;

}
