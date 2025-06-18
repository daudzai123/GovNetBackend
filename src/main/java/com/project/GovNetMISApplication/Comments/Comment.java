package com.project.GovNetMISApplication.Comments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.Documents.Document;
import com.project.GovNetMISApplication.sendDocuments.SendDoc;
import com.project.GovNetMISApplication.user.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentText;
    private LocalDateTime commentDateTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "document_id")
    @JsonIgnoreProperties(
        value = { "appendantDocsList","userId","activate" },
        allowSetters = true
    )
    private Document documentId;
    private LocalDateTime status;
    @ManyToOne
    @JoinColumn(name = "sendDoc_id")
    @JsonIgnoreProperties(
        value = { "senderDepartment","receiverDepartment","senderUserid","parentSendDoc",
        "documentId"},
        allowSetters = true
    )
    private SendDoc sendDocId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(
        value = { "lastName","position","activate",
        "profilePath","email","password","downloadURL","otpCode",
        "otpExpiration","userType","role","getAuthorities" },
        allowSetters = true
    )
    private Users userId;
    @ManyToOne
    @JoinColumn(name = "receiver_department")
    @JsonIgnoreProperties(
        value = { "parentDepartmentId", "description","users" },
        allowSetters = true
    )
    private Department department;
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    @JsonIgnoreProperties(
        value = { "sendDocId", "userId","department" },
        allowSetters = true
    )
    private Comment parentCommentId;
}
