package com.project.DocumentMIS.services;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;


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
