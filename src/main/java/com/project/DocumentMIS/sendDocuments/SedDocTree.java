package com.project.DocumentMIS.sendDocuments;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.project.DocumentMIS.Documents.Enums.DocumentType;
import com.project.DocumentMIS.sendDocuments.Enums.documentStatus;
import com.project.DocumentMIS.sendDocuments.Enums.sendStatus;

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