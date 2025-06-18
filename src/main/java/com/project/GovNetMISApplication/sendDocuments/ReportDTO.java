package com.project.GovNetMISApplication.sendDocuments;

import com.project.GovNetMISApplication.Documents.Enums.DocumentType;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private Long docId = null;
    private String docNumber = null;
    private String subject = null;
    private String docStatus = null;
    private DocumentType docType = null;
    private String receiverDepartment = null;
    private String action = null;
    private String findings = null;
    private String targetOrganResponse = null;

}

