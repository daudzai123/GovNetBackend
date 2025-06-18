package com.project.GovNetMISApplication.ActivitiesLog;

import com.project.GovNetMISApplication.user.CurrentUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ActivityLog")
public class ActivityLogController {
    @Autowired
    private ActivityLogService activityLogService;
    @Autowired
    private ActivityLogRepository activityLogRepository;
    @Autowired
    private CurrentUserInfoService currentUserInfoService;
    @GetMapping("/all")
    public Page<ActivityLog> getFilteredActivityLogs(
            Pageable pageable,
            @RequestParam(required = false) String entityName,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String departmentName,
//            @RequestParam(required = false) LocalDateTime startDate,
//            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) String searchItem
            ){
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("id").descending()
        );
        Page<ActivityLog> logActivitiesFiltered = activityLogService.getLogActivitiesFiltered(entityName, action, userName, departmentName,searchItem, sortedPageable);
        return logActivitiesFiltered;
    }
    @GetMapping("/getDepartmentWise")
    public Page<ActivityLogResponseDTO> getFilteredActivityLogsDepartmentWise(
            Pageable pageable,
            @RequestParam(required = false) String entityName,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) List<Long> departmentIds,
            @RequestParam(required = false) String searchItem
    ){
        ActivityLogCriteria criteria=new ActivityLogCriteria();
        criteria.setEntityName(entityName);
        criteria.setAction(action);
        criteria.setLogsStartDate(startDate);
        criteria.setLogsEndDate(endDate);
        criteria.setSearchTerm(searchItem);
        List<Long> currentUserDepartmentIds = currentUserInfoService.getCurrentUserDepartmentIds();
        criteria.setDepartmentIds(currentUserDepartmentIds);
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("id").descending()
        );
        Page<ActivityLogResponseDTO> byCriteria = activityLogService.findByCriteria(criteria, sortedPageable);
//        Page<ActivityLog> logActivitiesFiltered = activityLogService.getLogOfActivitiesFiltered(criteria, pageable);
        return byCriteria;
    }
}
