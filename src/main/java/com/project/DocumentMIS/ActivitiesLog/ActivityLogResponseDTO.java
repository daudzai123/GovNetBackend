package com.project.DocumentMIS.ActivitiesLog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLogResponseDTO {
    private Long id;
    private String entityName;
    private String content;
    private String action;
    private LocalDateTime timestamp;
    private Long recordId;
    private String userName;
    private String departmentName;
    private List<Departmentdto> departmentDTO;
}
