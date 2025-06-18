package com.project.DocumentMIS.ActivitiesLog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLogCriteria {
    private String entityName;
    private String action;
    private LocalDate logsStartDate;
    private LocalDate logsEndDate;
    private String searchTerm;
    private List<Long> departmentIds;
}
