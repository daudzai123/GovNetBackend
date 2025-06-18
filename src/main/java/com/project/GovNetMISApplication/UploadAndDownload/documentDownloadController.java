package com.project.GovNetMISApplication.UploadAndDownload;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/download")
public class documentDownloadController {
    @Autowired
    private DocDownloadService docDownloadService;
    @RequestMapping("/{fileName:.+}")
    public String fileDownloading(HttpServletResponse response, @PathVariable String fileName) throws IOException {
        docDownloadService.downloadFile(response,fileName);
        return "File downloaded successfully";
    }
}