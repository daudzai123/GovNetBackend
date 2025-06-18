package com.project.DocumentMIS.DepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepServiceService {

    @Autowired
    private DepServiceRepo repository;

    public DepService save(DepService depService) {
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
