package com.project.DocumentMIS.Documents;

import com.project.DocumentMIS.Departments.Department;
import com.project.DocumentMIS.Documents.Enums.DocumentType;
import com.project.DocumentMIS.Documents.Enums.ReferenceTypes;
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
