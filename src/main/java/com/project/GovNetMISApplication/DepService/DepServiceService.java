package com.project.GovNetMISApplication.DepService;

import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.Departments.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepServiceService {

    @Autowired
    private DepServiceRepo repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepService save(DepService depService) {
        // Validate that department exists if set
        if (depService.getDepartment() != null && depService.getDepartment().getDepId() != null) {
            Department department = departmentRepository.findById(depService.getDepartment().getDepId())
                    .orElseThrow(() -> new RuntimeException("Department not found with ID: " + depService.getDepartment().getDepId()));
            depService.setDepartment(department);
        }

        return repository.save(depService);
    }

    public List<DepService> getAll() {
        return repository.findAll();
    }

    public DepService getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
