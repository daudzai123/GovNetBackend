package com.project.GovNetMISApplication.ActivitiesLog;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog,Long>{

    // Custom query for filtering based on entity name, department name, and date range
    @Query("SELECT a FROM ActivityLog a " +
            "WHERE (:entityName is null or a.entityName = :entityName) " +
            "AND (:action is null or a.action = :action) " +
            "AND (:userName is null or a.userName = :userName) " +
            "AND (:departmentName is null or a.departmentName = :departmentName) "+
//            "AND (:startDate is null or a.timestamp >= :startDate) " +
//            "AND (:endDate is null or a.timestamp <= :endDate) " )
//            "AND (:endDate is null or a.timestamp <= :endDate) " +
            "AND (:search is null or LOWER(a.userName) LIKE %:search% OR LOWER(a.action) LIKE %:search% OR LOWER(a.entityName) LIKE %:search% OR LOWER(a.departmentName) LIKE %:search%)")
    Page<ActivityLog> filterActivityLogs(
            @Param("entityName") String entityName,
            @Param("action") String action,
            @Param("userName") String userName,
            @Param("departmentName") String departmentName,
//            @Param("startDate") LocalDateTime startDate,
//            @Param("endDate") LocalDateTime endDate,
            @Param("search") String search,
            Pageable pageable
    );

    default Specification<ActivityLog> buildSpecification(ActivityLogCriteria criteria){
        return (Root<ActivityLog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Add predicates based on the provided criteria

            // Example: Filter by entity name
            if (criteria.getEntityName() != null && !criteria.getEntityName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("entityName"), "%" + criteria.getEntityName() + "%"));
            }

            // Example: Filter by action
            if (criteria.getAction() != null && !criteria.getAction().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("action"), "%" + criteria.getAction() + "%"));
            }

            // Example: Filter by logs start date and end date
            if (criteria.getLogsStartDate() != null && criteria.getLogsEndDate() != null) {
                predicates.add(criteriaBuilder.between(root.get("logDate"), criteria.getLogsStartDate(), criteria.getLogsEndDate()));
            }

            // Example: Filter by search term
            if (criteria.getSearchTerm() != null && !criteria.getSearchTerm().isEmpty()) {
                String likeTerm = "%" + criteria.getSearchTerm() + "%";
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("entityName"), likeTerm),
                        criteriaBuilder.like(root.get("action"), likeTerm)
                        // Add more fields if needed
                ));
            }

            // Example: Filter by department IDs
//            if (criteria.getDepartmentIds() != null && !criteria.getDepartmentIds().isEmpty()) {
//                predicates.add(root.get("departmentId").in(criteria.getDepartmentIds()));
//            }
//            if (criteria.getDepartmentIds() != null && !criteria.getDepartmentIds().isEmpty()) {
//                predicates.add(criteriaBuilder.in(root.get("departmentList").get("depId"))
//                        .value(criteria.getDepartmentIds()));
//            }
            // Example: Filter by department IDs
            if (criteria.getDepartmentIds() != null && !criteria.getDepartmentIds().isEmpty()) {
                predicates.add(root.join("departmentList").get("depId").in(criteria.getDepartmentIds()));
            }


            // Combine all predicates with AND
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

            return query.getRestriction();
        };
    }

    Page<ActivityLog> findAll(Specification<ActivityLog> activityLogSpecification, Pageable pageable);
}
