package com.project.GovNetMISApplication.DocumentReport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import com.project.GovNetMISApplication.sendDocuments.Enums.documentStatus;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocReportResponseDTO {
    private Long docReportId;
    private String reportTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private String targetOrganResponse;
    private String findings;
    private documentStatus docStatus;
    private String action;
    private String docPath;
    private String downloadUrl;
    private String departmentCreatedBy;
}
