package com.project.GovNetMISApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/db")
public class ApplicationServices {
    @Value("${backup.path}")
    private String backupPath;

    @Autowired
    BackupService backupService;

    @GetMapping(value = "/backup")
    public byte[] backupDB() {
        return backupService.createBackup();
    }

    
}
