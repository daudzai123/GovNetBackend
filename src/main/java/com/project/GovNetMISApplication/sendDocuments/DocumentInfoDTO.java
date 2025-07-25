package com.project.GovNetMISApplication.sendDocuments;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.GovNetMISApplication.Documents.Enums.DocumentType;
import com.project.GovNetMISApplication.Documents.Enums.ExecutionType;
import com.project.GovNetMISApplication.user.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DocumentInfoDTO {
    private Long docId;
    private String docNumber;
    private String docNumber2;
    private String docName;
    private String subject;
    private String reference;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate receivedDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonIgnore
    private LocalDate updateDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    @Enumerated(EnumType.STRING)
    private ExecutionType executionType;
    @Enumerated(EnumType.STRING)
    private DocumentType docType;
    private Users userId;
}
