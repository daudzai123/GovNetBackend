package com.project.GovNetMISApplication.services;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



@Service
public class FileUploadService {

    @Value("${file.upload-dir}")
    private String UPLOAD_DIRECTORY;

    public Path uploadSingleFile(MultipartFile file) throws IOException {
        String uniqueFileName = generateUniqueFileName(file.getOriginalFilename(),UPLOAD_DIRECTORY);
        String filePath = UPLOAD_DIRECTORY + uniqueFileName;

        // Construct the full file path
        Path filesPath = Paths.get(filePath);
        Files.write(filesPath, file.getBytes());

        return filesPath; // Return the file path as a string
    }



    private String generateUniqueFileName(String originalFileName, String directory) {
        String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        // Generate a unique file name by appending a timestamp
        String uniqueFileName = fileNameWithoutExtension +"_"+ System.currentTimeMillis() + fileExtension;
        Path filePath = Paths.get(directory, uniqueFileName);
        if (Files.exists(filePath)) {
            return generateUniqueFileName(originalFileName, directory);
        }

        return uniqueFileName;
    }

    public boolean deleteFiles(String uniqueFileName) {
        String filePath = UPLOAD_DIRECTORY + uniqueFileName;
        try {
            Path fileToDelete = Paths.get(filePath);
            Files.deleteIfExists(fileToDelete);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
