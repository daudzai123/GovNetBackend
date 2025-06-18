package com.project.GovNetMISApplication.Documents;

import org.springframework.stereotype.Service;

@Service
public class PathToDownloadUrlService {
    public String getDownloadUrl(String path) {
        if (path != null) {
            String downloadUrl = path.substring(path.lastIndexOf("\\") + 1).replaceAll(" ", "%20");
            return "/download/" + downloadUrl;
        } else {
            return null;
        }
    }
    public String getAccessUrl(String path) {
        if (path != null) {
            String downloadUrl = path.substring(path.lastIndexOf("\\") + 1);
            return downloadUrl;
        } else {
            return null;
        }
    }
}
