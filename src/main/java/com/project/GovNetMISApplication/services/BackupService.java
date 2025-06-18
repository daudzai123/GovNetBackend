package com.project.GovNetMISApplication.services;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.zip.*;

@Service
public class BackupService {
    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${backup.path}")
    private String backupPath;
    

    public String generateBackup() throws IOException, InterruptedException {
        String backupFileName = "backup-" + System.currentTimeMillis() + ".sql";
        String backupFilePath = backupPath + backupFileName;

        String command = "C:/Program Files/PostgreSQL/15/bin/pg_dump -h localhost -U " + username + " -d DMS_Test -F c -b -v -f " + backupFilePath;
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.environment().put("PGPASSWORD", password);

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        if (exitCode == 0) {
            System.out.println("Backup completed successfully!");
            return backupFileName;
        } else {
            System.err.println("Backup failed!");
            return null;
        }
    }

    // for restore : pg_restore --dbname=your_database_name --username=your_username --password=your_password --verbose your_backup_file.backup


    private static final String STATIC_FILES_DIRECTORY = "src/main/resources/uploads/";
    public byte[] createBackup() {
        try {
            // Backup database
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", "src/pg_dump -U postgres DMS_Test");
            pb.redirectOutput(ProcessBuilder.Redirect.PIPE);
            pb.environment().put("PGPASSWORD", password);
            Process process = pb.start();
            InputStream dbBackupStream = process.getInputStream();

            // Backup static files
            File staticFilesDir = new File("src/main/resources/uploads/");
            File[] staticFiles = staticFilesDir.listFiles();
            ByteArrayOutputStream staticFilesZip = new ByteArrayOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(staticFilesZip);

            for (File file : staticFiles) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(zipEntry);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, length);
                }
                fileInputStream.close();
            }
            zipOutputStream.close();

            // Combine database backup and static files into a single zip
            ByteArrayOutputStream combinedZip = new ByteArrayOutputStream();
            ZipOutputStream finalZipOutputStream = new ZipOutputStream(combinedZip);

            // Add database backup to final zip
            ZipEntry dbBackupEntry = new ZipEntry("database_backup.sql");
            finalZipOutputStream.putNextEntry(dbBackupEntry);
            byte[] dbBuffer = new byte[1024];
            int dbLength;
            while ((dbLength = dbBackupStream.read(dbBuffer)) > 0) {
                finalZipOutputStream.write(dbBuffer, 0, dbLength);
            }
            dbBackupStream.close();

            // Add static files zip to final zip
            ZipEntry staticFilesEntry = new ZipEntry("static_files.zip");
            finalZipOutputStream.putNextEntry(staticFilesEntry);
            finalZipOutputStream.write(staticFilesZip.toByteArray());

            finalZipOutputStream.close();

            return combinedZip.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String restoreBackup(MultipartFile backupFile) {
        try {
            byte[] bytes = backupFile.getBytes();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);

            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String fileName = entry.getName();
                long size = entry.getSize();
                if (size >= 0) {
                    byte[] content = new byte[(int) size];
                    int bytesRead = zipInputStream.read(content);
                    if (bytesRead != size) {
                        throw new IOException("Error reading entry: " + fileName);
                    }
                    if (fileName.equals("database_backup.sql")) {
                        // Restore database
                        String restoreResult = restoreDatabase(content);
                        if (!restoreResult.startsWith("Error")) {
                            return "Database restoration completed successfully.";
                        } else {
                            return restoreResult;
                        }
                    } else if (fileName.equals("static_files.zip")) {
                        // Extract static files zip to the uploads folder within the static files directory
                        extractZip(content, STATIC_FILES_DIRECTORY);
                    }
                } else {
                    // Handle the case where the size is unknown or negative
                    // For example, log a warning or skip processing this entry
                    System.err.println("Warning: Unknown size for entry: " + fileName);
                }
                zipInputStream.closeEntry();
            }
            zipInputStream.close();
            return "Restore completed successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during restore: " + e.getMessage();
        }
    }

    private String restoreDatabase(byte[] sqlDumpContent) {
        try {
            // Write the SQL dump content to a temporary file
            File tempFile = File.createTempFile("database_backup", ".sql");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(sqlDumpContent);
            }

            // Execute psql command to restore the database
            ProcessBuilder pb = new ProcessBuilder(
                    "psql", "-U", "postgres", "MCIT-GDPES", "-f", tempFile.getAbsolutePath()
            );
            Process process = pb.start();
            process.waitFor();
            return "Database restoration completed successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during database restoration: " + e.getMessage();
        }
    }

    private void extractZip(byte[] zipContent, String targetDirectoryPath) throws IOException {
        File targetDirectory = new File(targetDirectoryPath);
        targetDirectory.mkdirs();

        try (ByteArrayInputStream bais = new ByteArrayInputStream(zipContent);
             ZipInputStream zis = new ZipInputStream(bais)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String entryFileName = entry.getName();
                File entryFile = new File(targetDirectory, entryFileName);
                if (entry.isDirectory()) {
                    entryFile.mkdirs();
                } else {
                    try (FileOutputStream fos = new FileOutputStream(entryFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }
}