package com.project.DocumentMIS.Documents;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.DocumentMIS.Documents.Enums.DocumentType;
import com.project.DocumentMIS.Documents.Enums.ReferenceTypes;

import lombok.Data;

@Data
public class DocForLinkingDTO {
    private Long docId;
    private String docNumber;
    private String docNumber2;
    private String subject;
    private ReferenceTypes referenceType;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate secondDate;
    private DocumentType docType;
}
