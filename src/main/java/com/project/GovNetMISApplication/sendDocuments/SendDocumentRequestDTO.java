package com.project.GovNetMISApplication.sendDocuments;

import com.project.GovNetMISApplication.sendDocuments.Enums.Secretness;
import com.project.GovNetMISApplication.sendDocuments.Enums.documentStatus;
import jakarta.annotation.Nullable;
import lombok.*;

import java.util.Set;

@Data
public class SendDocumentRequestDTO {
    private Long sendDocId;
    private Secretness secret;
    private String guide;
    private boolean sendOriginalDocument;
    private boolean sendAppendentDocuments;
    private documentStatus docStatus;
    private Long documentId;
    private Set<Long> receiverDepartment;
    private Long senderDepartment;
    @Nullable
    private Long parentSendDoc;
}
