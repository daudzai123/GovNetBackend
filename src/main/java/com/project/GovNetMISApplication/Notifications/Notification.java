package com.project.GovNetMISApplication.Notifications;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.GovNetMISApplication.Comments.Comment;
import com.project.GovNetMISApplication.Documents.Document;
import com.project.GovNetMISApplication.sendDocuments.SendDoc;
import com.project.GovNetMISApplication.user.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 5000)
    private String content;
    private String notificationType;
    private LocalDateTime created_at;
    private LocalDateTime read_at;
    @ManyToOne
    @JoinColumn(name = "recipient")
    @JsonIgnoreProperties(value = { "lastName", "position", "activate", "department",
            "profilePath", "email", "password", "downloadURL", "otpCode",
            "otpExpiration", "userType", "role", "getAuthorities" }, allowSetters = true)
    private Users recipient;

    @SuppressWarnings("unchecked")
    public Map<String, Object> getDataMap() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(this.content, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Notification(Comment comment, Users recipient, String link) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", comment.getCommentId());
        map.put("commentor", comment.getUserId().getFirstName());
        map.put("document", comment.getDocumentId().getSubject());
        map.put("document_id", comment.getDocumentId().getDocId());
        map.put("commentor_id", comment.getDocumentId().getDocId());
        map.put("link", link + comment.getSendDocId().getSendDocId());
        map.put("message",
                comment.getUserId().getFirstName() + " در مورد " + comment.getDocumentId().getSubject() + " نظر داد.");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.content = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            this.content = "[]";
        }
        this.notificationType = comment.getClass().getSimpleName();
        this.created_at = LocalDateTime.now();
        this.recipient = recipient;
    }

    public Notification(SendDoc sendDoc, Users recipient) {
        Map<String, String> map = new HashMap<>();
        map.put("id", sendDoc.getSendDocId() + "");
        map.put("sender_dep", sendDoc.getSenderDepartment().getDepName());
        map.put("document", sendDoc.getDocumentId().getSubject());
        map.put("document_id", sendDoc.getDocumentId().getDocId() + "");
        map.put("sender_user", sendDoc.getSenderUserid().getFirstName());
        map.put("link", "/incoming-documents/" + sendDoc.getSendDocId());
        map.put("message", "سند " + sendDoc.getDocumentId().getSubject() + " را از "
                + sendDoc.getSenderDepartment().getDepName() + " دریافت کردید.");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.content = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            this.content = "[]";
        }
        this.notificationType = sendDoc.getClass().getSimpleName();
        this.created_at = LocalDateTime.now();
        this.recipient = recipient;
    }

    public Notification(Users user, SendDoc sendDoc, Map<String, Object> map1) {
        Map<String, String> map = new HashMap<>();
        map.put("id", sendDoc.getSendDocId() + "");
        map.put("sender_dep", sendDoc.getSenderDepartment().getDepName());
        map.put("document", sendDoc.getDocumentId().getSubject());
        map.put("document_id", sendDoc.getDocumentId().getDocId() + "");
        map.put("sender_user", sendDoc.getSenderUserid().getFirstName());
        map.put("link", "/incoming-documents/" + sendDoc.getSendDocId());
        if (map1 != null) {
            map.put("changes", map1.toString());
        }
        map.put("message", "هدایت " + sendDoc.getDocumentId().getSubject() + " توسط "
                + sendDoc.getSenderDepartment().getDepName() + " ویرایش گردید.");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.content = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            this.content = "[]";
        }
        this.notificationType = "SendDoc_Update";
        this.created_at = LocalDateTime.now();
        this.recipient = user;
    }

    public Notification(Users user, Document document, Map<String, Object> map1) {
        Map<String, String> map = new HashMap<>();
        map.put("id", document.getDocId() + "");
        map.put("document", document.getSubject());
        map.put("document_no", document.getDocNumber() + "");
        if (map1 != null) {
            map.put("link", "/incoming-documents/" + map1.get("sendDoc_id"));
            map.put("changes", map1.toString());
        }
        map.put("message",
                "سند " + document.getSubject() + " توسط " + document.getDepartment().getDepName() + " ویرایش گردید.");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.content = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            this.content = "[]";
        }
        this.notificationType = "Doc_Update";
        this.created_at = LocalDateTime.now();
        this.recipient = user;
    }

    public Notification(Users user, Users recipient) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("roles", user.getEmail());
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("position", user.getPosition());
        map.put("link", "/users/" + user.getId());
        map.put("message", "کاربر جدید " + user.getFirstName() + " " + user.getLastName() + " .به سیستم اضافه شد");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.content = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            this.content = "[]";
        }
        this.notificationType = user.getClass().getSimpleName();
        this.created_at = LocalDateTime.now();
        this.recipient = recipient;
    }

}
