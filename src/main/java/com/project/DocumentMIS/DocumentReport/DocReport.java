package com.project.DocumentMIS.DocumentReport;

import com.project.DocumentMIS.sendDocuments.SendDoc;
import com.project.DocumentMIS.sendDocuments.Enums.documentStatus;
import com.project.DocumentMIS.user.Users;
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
public class DocReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docReportId;
    private String reportTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    @Column(length = 4000)
    private String targetOrganResponse;
    @Column(length = 6000)
    private String findings;
    private documentStatus docStatus;
    @Column(length = 4000)
    private String action;
    private String docPath;
    private String downloadUrl;
    @ManyToOne
    private SendDoc sendDoc;
    @ManyToOne
    private Users user;
}