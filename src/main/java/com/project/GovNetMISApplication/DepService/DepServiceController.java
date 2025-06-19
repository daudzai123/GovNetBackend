package com.project.GovNetMISApplication.DepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dep-services")
public class DepServiceController {

    @Autowired
    private DepServiceService service;

    /** Create or update a DepService */
    @PostMapping
    public DepService createOrUpdate(@RequestBody DepService depService) {
        return service.save(depService);
    }

    /** Get all DepServices */
    @GetMapping
    public List<DepService> getAll() {
        return service.getAll();
    }

    /** Get a single DepService by its ID */
    @GetMapping("/{id}")
    public DepService getById(@PathVariable Long id) {
        return service.getById(id);
    }

    /** Delete a DepService by its ID */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
