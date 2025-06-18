package com.project.GovNetMISApplication.sendDocuments;


import com.project.GovNetMISApplication.Documents.Enums.DocumentType;
import com.project.GovNetMISApplication.sendDocuments.Enums.documentStatus;
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
