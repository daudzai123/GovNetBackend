package com.project.GovNetMISApplication.sendDocuments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.Documents.Document;
import com.project.GovNetMISApplication.sendDocuments.Enums.Secretness;
import com.project.GovNetMISApplication.sendDocuments.Enums.documentStatus;
import com.project.GovNetMISApplication.sendDocuments.Enums.sendStatus;
import com.project.GovNetMISApplication.user.Users;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SendDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sendDocId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendingDate;
    @Enumerated(EnumType.STRING)
    private Secretness secret;
    @Enumerated(EnumType.STRING)
    private sendStatus sendingStatus = sendStatus.PENDING; // set default value to pending
    private LocalDateTime timeToSeen;
    private String guide;
    @JsonIgnore
    private String targetOrganResponse;
    @JsonIgnore
    private String findings;
    @JsonIgnore
    private String action;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean sendAppendentDocs;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean sendOrginalDoc;
    @Enumerated(EnumType.STRING)
    private documentStatus docStatus;
    @JsonIgnore
    private String ancestor;
    @ManyToOne
    @JsonIgnoreProperties(value = { "creationDate", "updateDate", "docName" }, allowSetters = true)
    @JoinColumn(name = "doc_id")
    private Document documentId;
    // private DocumentInfoDTO documentInfoDTO;
    @ManyToOne
    @JsonIgnoreProperties(value = { "parentDepartmentId", "description" }, allowSetters = true)
    private Department senderDepartment;
    @ManyToOne
    @JsonIgnoreProperties(value = { "parentDepartmentId", "description", "users" }, allowSetters = true)
    private Department receiverDepartment;
    @ManyToOne
    @JsonIgnoreProperties(value = { "password", "profilePath", "downloadURL", "role", "email", "profilePath",
            "department", "userType" }, allowSetters = true)
    private Users senderUserid;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "secret", "documentId", "senderUserid", "parentSendDoc" }, allowSetters = true)
    @JoinColumn(name = "parent_SendDoc_Id")
    @Nullable
    private SendDoc parentSendDoc;
    public SendDoc(SendDoc sendDoc) {
        this.sendDocId = sendDoc.sendDocId;
        this.sendingDate = sendDoc.sendingDate;
        this.secret = sendDoc.secret;
        this.sendingStatus = sendDoc.sendingStatus;
        this.timeToSeen = sendDoc.timeToSeen;
        this.guide = sendDoc.guide;
        this.targetOrganResponse = sendDoc.targetOrganResponse;
        this.findings = sendDoc.findings;
        this.action = sendDoc.action;
        this.sendAppendentDocs = sendDoc.sendAppendentDocs;
        this.sendOrginalDoc = sendDoc.sendOrginalDoc;
        this.docStatus = sendDoc.docStatus;
        this.ancestor = sendDoc.ancestor;
        this.documentId = sendDoc.documentId;
        this.senderDepartment = sendDoc.senderDepartment;
        this.receiverDepartment = sendDoc.receiverDepartment;
        this.senderUserid = sendDoc.senderUserid;
        this.parentSendDoc = sendDoc.parentSendDoc;
    }

    public void markAsSeen() {
        this.sendingStatus = sendStatus.SEEN;
        this.timeToSeen = LocalDateTime.now();
    }
}