package com.project.GovNetMISApplication.Departments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
    int recordCount;
    Long totalRecords;
    T response;
}
