package com.project.GovNetMISApplication.sendDocuments;

import com.project.GovNetMISApplication.Documents.Enums.DocumentType;
import com.project.GovNetMISApplication.Documents.Enums.ReferenceTypes;
import com.project.GovNetMISApplication.sendDocuments.Enums.Secretness;
import com.project.GovNetMISApplication.sendDocuments.Enums.documentStatus;
import com.project.GovNetMISApplication.sendDocuments.Enums.sendStatus;
import jakarta.annotation.Nullable;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SendDocResponseDTO {
    private Long docId;
    private Long sendDocId;
    private String docNumber;
    private ReferenceTypes referenceType;
    private boolean sendOrginalDoc;
    private String subject;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private LocalDate deadline;
    private LocalDateTime sendingDate;
    private Secretness secret;
    private sendStatus sendingStatus;
    private LocalDateTime timeToSeen;
    private String guide;
    private documentStatus docStatus;
    private boolean withOrginalDoc; 
    private DocumentType docType;
    private String downloadUrl;
    private senderDepartmentDTO senderDepartment;
    private receiverDepartmentDTO receiverDepartments;
    private String impReference = "Not Applicable";
    @Nullable
    private Long parent_SendDoc_Id;
}
