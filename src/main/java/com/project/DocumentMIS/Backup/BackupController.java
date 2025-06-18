package com.project.DocumentMIS.Backup;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
import com.project.DocumentMIS.services.ReportService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/backup")
public class BackupController{
    private final DbBackupService backupService;

   @Autowired
   ReportService reportService;
    public BackupController(DbBackupService backupService) {
        this.backupService = backupService;
    }
    @GetMapping("/all")
    public List<BackupDB> allBackups(){
        return backupService.getAllBackup();
    }

   @GetMapping("/create")
   public void downloadBackup(HttpServletResponse response) throws IOException, InterruptedException {
        backupService.generateBackup(response);
   }

   @RequestMapping("/{fileName:.+}")
   public String BackupDownload(HttpServletResponse response, @PathVariable String fileName) throws IOException {
       backupService.downloadSql(response,fileName);
       return "Backup downloaded successfully";
   }
    @RequestMapping("/restore/{fileName:.+}")
    public String RestoreBackup(@PathVariable String fileName) throws IOException, InterruptedException {
        return backupService.RestoreDB(fileName);
    }

   @RequestMapping("/report")
   public String exportReport() throws FileNotFoundException, JRException {
       return reportService.exportReport("some");
   }
}