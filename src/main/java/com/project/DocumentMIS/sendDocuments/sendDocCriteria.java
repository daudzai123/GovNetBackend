package com.project.DocumentMIS.sendDocuments;

import java.time.LocalDate;
import java.util.List;

import com.project.DocumentMIS.Documents.Enums.DocumentType;
import com.project.DocumentMIS.Documents.Enums.ReferenceTypes;
import com.project.DocumentMIS.sendDocuments.Enums.documentStatus;
import lombok.Data;

@Data
public class sendDocCriteria {
    private String documentNo;
    private String docNumber2;
    private documentStatus docStatus;
    private LocalDate sendingDateStart;
    private LocalDate sendingDateEnd;
    private String subject;
    private ReferenceTypes docReference;
    private Long referenceId;
    private String searchTerm;
    private DocumentType documentType;
    private List<Long> senderDepartmentIds;
    private List<Long> receiverDepartmentIds;
    private Boolean ExcludeSecret = true;
}
