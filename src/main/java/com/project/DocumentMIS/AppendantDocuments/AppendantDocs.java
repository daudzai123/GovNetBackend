package com.project.DocumentMIS.AppendantDocuments;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AppendantDocs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appendantDocId;
    private String appendantDocName;
    private String appendantDocPath;
    private String appendantDocDownloadUrl;
//    public String getAppendantDocName() {
//        return appendantDocName;
//    }
//    public void setAppendantDocName(String appendantDocName) {
//        this.appendantDocName = appendantDocName;
//    }
//
//    public Long getAppendantDocId() {
//        return appendantDocId;
//    }
//
//    public void setAppendantDocId(Long appendantDocId) {
//        this.appendantDocId = appendantDocId;
//    }
//
//    public String getAppendantDocPath() {
//        return appendantDocPath;
//    }
//
//    public void setAppendantDocPath(String appendantDocPath) {
//        this.appendantDocPath = appendantDocPath;
//    }
//
//    public String getAppendantDocDownloadUrl() {
//        return appendantDocDownloadUrl;
//    }
//
//    public void setAppendantDocDownloadUrl(String appendantDocDownloadUrl) {
//        this.appendantDocDownloadUrl = appendantDocDownloadUrl;
//    }
}
