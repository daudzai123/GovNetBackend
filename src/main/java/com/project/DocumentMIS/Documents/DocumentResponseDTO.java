package com.project.DocumentMIS.Documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.DocumentMIS.Departments.Department;
import com.project.DocumentMIS.Documents.Enums.DocumentType;
import com.project.DocumentMIS.Documents.Enums.ExecutionType;
import com.project.DocumentMIS.Documents.Enums.ReferenceTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponseDTO {
    private Long docId;
    private String docNumber;
    private String docNumber2;
    private String subject;
    private String description = null;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate receivedDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate;
    private String docName;
    private ReferenceTypes referenceType;
    private ExecutionType executionType;
    private DocumentType docType;
    private Long userId;
    private String firstName;
    private String depName;
    private String docPath;
    private String impReference = "Not Applicable";
    private String downloadUrl;
    private List<String> appendantDocPath;
    private List<String> appendantDocDownloadUrl;
}
