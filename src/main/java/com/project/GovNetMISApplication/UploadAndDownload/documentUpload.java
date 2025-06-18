package com.project.GovNetMISApplication.UploadAndDownload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class documentUpload {

    @Value("${file.upload-dir}")
    private String UPLOAD_DIRECTORY;

    public String uploadSingleFile(MultipartFile file) throws IOException {
        String uniqueFileName = generateUniqueFileName(file.getOriginalFilename(), UPLOAD_DIRECTORY);
        String filePath = uniqueFileName + UPLOAD_DIRECTORY;
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());
        return filePath;
    }

    public List<String> uploadMultipleFiles(List<MultipartFile> files) throws IOException {
        List<String> docPaths = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            String uniqueDocName = generateUniqueFileName(files.get(i).getOriginalFilename(), UPLOAD_DIRECTORY);
            String documentPath = uniqueDocName + UPLOAD_DIRECTORY;
            Path docPath = Paths.get(documentPath);
            Files.write(docPath, files.get(i).getBytes());
            docPaths.add(documentPath);
        }
        return docPaths;
    }

    public List<String> storeFiles(MultipartFile mainDocument, List<MultipartFile> appendantDocuments) {
        List<String> filePaths = new ArrayList<>();
        // Handle main document upload
        if (mainDocument != null) {
            try {
                String fileName = StringUtils.cleanPath(mainDocument.getOriginalFilename());
                Path targetLocation = Paths.get(UPLOAD_DIRECTORY).resolve(fileName);
                Files.copy(mainDocument.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                filePaths.add(targetLocation.toString());

            } catch (IOException ex) {
                // Handle file upload exception
                throw new RuntimeException("Failed to store main document.", ex);
            }
        }
        // Handle appendant documents upload
        if (appendantDocuments != null && !appendantDocuments.isEmpty()) {
            for (MultipartFile file : appendantDocuments) {
                try {
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    Path targetLocation = Paths.get(UPLOAD_DIRECTORY).resolve(fileName);
                    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                    filePaths.add(targetLocation.toString());
                } catch (IOException ex) {
                    // Handle file upload exception
                    throw new RuntimeException("Failed to store appendant document.", ex);
                }
            }
        }
        return filePaths;
    }

    public void init() {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIRECTORY));
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public String generateUniqueFileName(String originalFileName, String directory) {
        String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));

        // Generate a unique file name by appending a timestamp
        String uniqueFileName = fileNameWithoutExtension + "_" + System.currentTimeMillis() + fileExtension;
        Path filePath = Paths.get(directory, uniqueFileName);

        // Check if the file already exists, and if so, generate a new name recursively
        if (Files.exists(filePath)) {
            return generateUniqueFileName(originalFileName, directory);
        }

        return uniqueFileName;
    }
}