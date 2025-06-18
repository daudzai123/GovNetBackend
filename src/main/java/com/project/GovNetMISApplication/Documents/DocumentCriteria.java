package com.project.GovNetMISApplication.Documents;

import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.Documents.Enums.DocumentType;
import com.project.GovNetMISApplication.Documents.Enums.ReferenceTypes;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DocumentCriteria {
    private String docNumber;
    private String docNumber2;
    private Long referenceId;
    private String subject;
    private ReferenceTypes referenceType;
    private DocumentType docType;
    private LocalDate documentCreationStartDate;
    private LocalDate documentCreationEndDate;
    private String searchTerm;
    private List<Department> department;
}
