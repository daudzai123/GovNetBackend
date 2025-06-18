package com.project.GovNetMISApplication.Documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.GovNetMISApplication.AppendantDocuments.AppendantDocs;
import com.project.GovNetMISApplication.Documents.Enums.DocumentType;
import com.project.GovNetMISApplication.Documents.Enums.ExecutionType;
import com.project.GovNetMISApplication.Documents.Enums.ReferenceTypes;
import com.project.GovNetMISApplication.UploadAndDownload.documentUpload;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private Long docId;
    private String docNumber;
    private String docNumber2;
    private String subject;
    private String description;
    private Long reference;
    private ReferenceTypes referenceType;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate secondDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate receivedDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate;
    private String docName;
    private ExecutionType executionType;
    private DocumentType docType;
    private Long userId;
    private Long department;
    private List<AppendantDocs> appendantDocsList;
    private List<Long> linkingDocs;
    private MultipartFile mainDocument;
    private List<MultipartFile> appendantDocuments;
    @Autowired
    private documentUpload  docUpload;
    public void uploadFiles(documentUpload docUpload) {
//        this.mainDocumentPaths =
                docUpload.storeFiles(mainDocument, appendantDocuments);
    }
}