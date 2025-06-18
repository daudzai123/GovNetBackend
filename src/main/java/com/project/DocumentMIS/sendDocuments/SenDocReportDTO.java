package com.project.DocumentMIS.sendDocuments;


import com.project.DocumentMIS.Documents.Enums.DocumentType;
import com.project.DocumentMIS.sendDocuments.Enums.documentStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SenDocReportDTO {
    private Long docId;
    private Long sendDocId;
    private String docNumber;
    private String subject;
    private LocalDate deadline;
    private LocalDateTime sendingDate;
    private documentStatus docStatus;
    private DocumentType docType;
    private senderDepartmentDTO senderDepartment;
    private receiverDepartmentDTO receiverDepartments;
    private String action;
    private String findings;
    private String targetOrganResponse;
}
