package com.project.DocumentMIS.Documents;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.DocumentMIS.AppendantDocuments.AppendantDocs;
import com.project.DocumentMIS.Departments.Department;
import com.project.DocumentMIS.DocReference.DocReference;
import com.project.DocumentMIS.Documents.Enums.DocumentType;
import com.project.DocumentMIS.Documents.Enums.ExecutionType;
import com.project.DocumentMIS.Documents.Enums.ReferenceTypes;
import com.project.DocumentMIS.sendDocuments.SendDoc;
import com.project.DocumentMIS.user.Users;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docId;
    private String docNumber;
    @JsonIgnore
    private String docName;
    private String docNumber2;
    private String subject;
    @Column(length = 3000)
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate secondDate;
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
    private String docPath;
    @Enumerated(EnumType.STRING)
    private ReferenceTypes referenceType;
    private String downloadUrl;
    @Enumerated(EnumType.STRING)
    private DocumentType docType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "password", "profilePath", "downloadURL", "role", "email",
            "userType" }, allowSetters = true)
    @JoinColumn(name = "userId")
    private Users userId;

    @ManyToOne
    @JoinColumn(name = "refId")
    private DocReference reference;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department")
    @JsonIgnoreProperties(value = { "description", "parentDepartmentId", "users" }, allowSetters = true)
    private Department department;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "docId")
    private List<AppendantDocs> appendantDocsList;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "documentId")
    @JsonIgnoreProperties(value = { "sendingDate", "guide", "ancestor", "documentId", "senderDepartment",
            "receiverDepartment", "senderUserid",
            "parentSendDoc" }, allowSetters = true)
    private List<SendDoc> sendDocList;

    public Document(Document document) {
        this.docNumber = document.getDocNumber();
        this.docName = document.getDocName();
        this.docNumber2 = document.getDocNumber2();
        this.subject = document.getSubject();
        this.initialDate = document.getInitialDate();
        this.secondDate = document.getSecondDate();
        this.receivedDate = document.getReceivedDate();
        this.creationDate = document.getCreationDate();
        this.updateDate = document.getUpdateDate();
        this.deadline = document.getDeadline();
        this.executionType = document.getExecutionType();
        this.docPath = document.getDocPath();
        this.referenceType = document.getReferenceType();
        this.downloadUrl = document.getDownloadUrl();
        this.docType = document.getDocType();
        this.userId = document.getUserId();
        this.reference = document.getReference();
        this.department = document.getDepartment();
        // For list fields, you might need to create copies of individual items if
        // necessary
        // For simplicity, I'm assuming the lists contain immutable objects or don't
        // need deep copies
        this.appendantDocsList = document.getAppendantDocsList();
        this.sendDocList = document.getSendDocList();
    }
}
