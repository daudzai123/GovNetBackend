package com.project.DocumentMIS.sendDocuments;

import com.project.DocumentMIS.sendDocuments.Enums.Secretness;
import com.project.DocumentMIS.sendDocuments.Enums.documentStatus;
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
