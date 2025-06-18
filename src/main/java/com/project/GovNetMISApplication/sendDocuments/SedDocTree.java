package com.project.GovNetMISApplication.sendDocuments;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.project.GovNetMISApplication.Documents.Enums.DocumentType;
import com.project.GovNetMISApplication.sendDocuments.Enums.documentStatus;
import com.project.GovNetMISApplication.sendDocuments.Enums.sendStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SedDocTree {
    private Long docId;
    private Long sendDocId;
    private String docNumber;
    private String subject;
    private LocalDate creationDate;
    private LocalDateTime sendingDate;
    private sendStatus sendingStatus;
    private documentStatus docStatus;
    private DocumentType docType;
    private String downloadUrl;
    private senderDepartmentDTO senderDepartment;
}