package com.project.GovNetMISApplication.ActivitiesLog;

import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.user.CurrentUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityLogService {
    @Autowired
    private ActivityLogRepository activityLogRepository;
    @Autowired
    private CurrentUserInfoService currentUserInfoService;
    public void logActivity(String entityName, Long recordId, String action,List<Department> departmentList) {
//        System.out.println("THIS IS THE ONE WE WANT "+departmentList.get(0).getDepName());
//        System.out.println("----------AND SIZE OF DEPARTMENT"+departmentList.size()+" AND THIS IS LIST "+departmentList);
        String currentUserFirstName = currentUserInfoService.getCurrentUserFirstName();
        List<String> depNames = new ArrayList<>();
        if (currentUserInfoService.getCurrentUserDepartmentIds() != null) {
            depNames = currentUserInfoService.getCurrentUserDepartments().stream().map(department -> {
                String depName = department.getDepName();
                return depName;
            }).collect(Collectors.toList());
        }
        ActivityLog activityLog = new ActivityLog();
        activityLog.setTimestamp(LocalDateTime.now());
        activityLog.setAction(action);
        activityLog.setEntityName(entityName);
        activityLog.setRecordId(recordId);
        activityLog.setUserName(currentUserFirstName);
        activityLog.setDepartmentList(new ArrayList<>(departmentList));
        if (depNames.size() == 1) {
            activityLog.setDepartmentName(depNames.get(0));
        }
        if (depNames.size() == 0) {
            activityLog.setDepartmentName("Admin");
        }
        if (depNames.size() > 1) {
            activityLog.setDepartmentName(currentUserInfoService.getCurrentUser().getUserType().toString());
        }
        activityLogRepository.save(activityLog);
    }
    public void logsActivity(String entityName, Long recordId, String action, List<Department> departmentList, String content) {
//

        String currentUserFirstName = currentUserInfoService.getCurrentUserFirstName();

        List<String> depNames = new ArrayList<>();
        if (currentUserInfoService.getCurrentUserDepartmentIds() != null) {
            depNames = currentUserInfoService.getCurrentUserDepartments().stream().map(department -> {
                String depName = department.getDepName();
                return depName;
            }).collect(Collectors.toList());
        }



        ActivityLog activityLog = new ActivityLog();
        activityLog.setTimestamp(LocalDateTime.now());
        activityLog.setAction(action);
        activityLog.setEntityName(entityName);
        activityLog.setRecordId(recordId);
        activityLog.setUserName(currentUserFirstName);
        activityLog.setContent(content);
        activityLog.setDepartmentList(new ArrayList<>(departmentList));
//        activityLog.setDetails(details);
        if (depNames.size() == 1) {
            activityLog.setDepartmentName(depNames.get(0));
        }
        if (depNames.size() == 0) {
            activityLog.setDepartmentName("Admin");
        }
        if (depNames.size() > 1) {
            activityLog.setDepartmentName(currentUserInfoService.getCurrentUser().getUserType().toString());
        }
        activityLogRepository.save(activityLog);
    }

    public Page<ActivityLog> getLogOfActivitiesFiltered(String entityName, String action, String userName, String departmentName, String searchItem, Pageable pageable) {
        return null;
    }

    public Page<ActivityLog> getLogActivitiesFiltered(String entityName, String action, String userName, String departmentName, String searchItem, Pageable pageable) {
//        LocalDateTime startDateTime =startDate != null ? startDate.atStartOfDay() : null;
//        LocalDateTime endDateTime = endDate != null ? endDate.atTime(23, 59, 59) : null;
//        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;
        Page<ActivityLog> activityLogs = activityLogRepository.filterActivityLogs(entityName, action, userName, departmentName, searchItem, pageable);
        List<ActivityLog> content = activityLogs.getContent();
        return new PageImpl<>(content,pageable,activityLogs.getTotalElements());
    }

    public Page<ActivityLogResponseDTO> findByCriteria(ActivityLogCriteria criteria, Pageable pageable) {
        Specification<ActivityLog> activityLogSpecification = activityLogRepository.buildSpecification(criteria);
        Page<ActivityLog> all = activityLogRepository.findAll(activityLogSpecification, pageable);
        List<ActivityLogResponseDTO> collect = all.getContent().stream().map(activityLog -> {
            ActivityLogResponseDTO activityLogResponseDTO = mapEntityToDTO(activityLog);
            return activityLogResponseDTO;
        }).collect(Collectors.toList());
        return new PageImpl<>(collect,pageable,all.getTotalElements());
    }
    private ActivityLogResponseDTO mapEntityToDTO(ActivityLog activityLog){
        ActivityLogResponseDTO dto=new ActivityLogResponseDTO();
        dto.setEntityName(activityLog.getEntityName());
        dto.setAction(activityLog.getAction());
//        dto.setContent(activityLog.getContent());
        dto.setTimestamp(activityLog.getTimestamp());
        dto.setRecordId(activityLog.getRecordId());
        dto.setUserName(activityLog.getUserName());
        if (activityLog.getDepartmentName() != null){
            dto.setDepartmentName(activityLog.getDepartmentName());
        }
        if (activityLog.getDepartmentList() !=null){
            List<Departmentdto> collect = activityLog.getDepartmentList().stream().map(department -> {
                Departmentdto departmentdto = new Departmentdto(department.getDepId(), department.getDepName());
                return departmentdto;
            }).collect(Collectors.toList());
            dto.setDepartmentDTO(collect);
        }
        return dto;
    }
}
