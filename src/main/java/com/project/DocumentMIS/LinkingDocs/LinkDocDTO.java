package com.project.DocumentMIS.LinkingDocs;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.DocumentMIS.Documents.Enums.DocumentType;
import com.project.DocumentMIS.Documents.Enums.ReferenceTypes;
import lombok.Data;

@Data
public class LinkDocDTO {
    private Long id;
    private Long docId;
    private Long sendDocId;
    private String docNumber;
    private String docNumber2;
    private String subject;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate secondDate;
    private DocumentType docType;
    private ReferenceTypes referenceType;
    private boolean ownerDep;
}
