package com.project.GovNetMISApplication.DocumentReport;

import com.project.GovNetMISApplication.sendDocuments.Enums.documentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocReportRequestDTO {
    private Long docReportId;
    private String reportTitle;
    private documentStatus docStatus;
    private String targetOrganResponse;
    private String findings;
    private String action;
    private Long sendDocId;
}
