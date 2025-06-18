package com.project.DocumentMIS.Backup;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.DocumentMIS.Exceptions.MyNotFoundException;
import com.project.DocumentMIS.user.CurrentUserInfoService;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class DbBackupService {

    private final DbBackupRepository backupRepository;
    private final CurrentUserInfoService currentUserInfoService;
    @Autowired
    public DbBackupService(DbBackupRepository backupRepository, CurrentUserInfoService currentUserInfoService) {
        this.backupRepository = backupRepository;
        this.currentUserInfoService = currentUserInfoService;
    }

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${datasource.name}")
    private String dbname;

    @Value("${pgdump.address}")
    private String pgdump;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${backup.path}")
    private String backupPath;

	public List<BackupDB> getAllBackup() {
		return backupRepository.findAll();
	}

    public String DeleteBackup(Long id) {
		 BackupDB db = backupRepository.findById(id).orElseThrow(()-> new MyNotFoundException("Not found!"));
         try {
            Path path = Paths.get(filePath);

            // Delete the file
            Files.delete(path);

            System.out.println("File removed successfully.");
            return "backup file deleted successfully.";
        } catch (Exception e) {
            // Handle exceptions, such as FileNotFound or IOException
            System.err.println("Error removing file: " + e.getMessage());
            return "backup file not deleted.";
        }


	}

    String filePath = "path/to/your/file.txt";

    public void downloadSql(HttpServletResponse response, String fileName) throws FileNotFoundException, IOException {
        if (fileName != null) {
            String backupFilePath = backupPath + fileName;
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + backupFilePath);
            try (FileInputStream fis = new FileInputStream(new File(backupFilePath));
                 OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("Backup Creation faild!");
        }
    }

    public void generateBackup(HttpServletResponse response) throws IOException, InterruptedException {
        String backupFileName = "backup-" + System.currentTimeMillis() + ".sql";
        String backupFilePath = backupPath + backupFileName;

        String command = pgdump +" -h localhost -U " + username + " -d "+ dbname +" -F c -b -v -f " + backupFilePath;
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.environment().put("PGPASSWORD", password);

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        if (exitCode == 0) {
            BackupDB db = new BackupDB();
            db.setBackupPath(backupFileName);
            db.setCreated_at(LocalDateTime.now());
            db.setCreator(currentUserInfoService.getCurrentUser());
            backupRepository.save(db);
            System.out.println("Backup completed successfully!");
             downloadSql(response, backupFileName);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("the process faild");
            System.err.println("Backup failed!");
        }
    }

	public String RestoreDB(String fileName) throws IOException, InterruptedException {
		String restoreFilePath = backupPath + fileName;
        String command = "C:/Program Files/PostgreSQL/16/bin/pg_restore -h localhost -U " + username + " -d DMS_Update1 --clean -v " + restoreFilePath;
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.environment().put("PGPASSWORD", password);

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        if (exitCode == 0) {
            System.out.println("Restore completed successfully!");
            return "Restore completed successfully!";
        } else {
            System.err.println("Restore failed!");
            return "Restore failed!";
        }
        // pg_restore --dbname=your_database_name --username=your_username --password=your_password --verbose your_backup_file.backup
	}

}
