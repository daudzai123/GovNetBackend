package com.project.DocumentMIS.sendDocuments;

import com.project.DocumentMIS.sendDocuments.Enums.documentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDocumentStatusRequest {
    private documentStatus docStatus;
}
