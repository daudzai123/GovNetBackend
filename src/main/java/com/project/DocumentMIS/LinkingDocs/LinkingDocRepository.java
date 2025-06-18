package com.project.DocumentMIS.LinkingDocs;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkingDocRepository extends JpaRepository<LinkingDoc,Long>{

    @Query("SELECT ld FROM LinkingDoc ld WHERE ld.first = :id OR ld.second = :id")
    List<LinkingDoc> findByFirstDocOrSecondDoc(@Param("id") Long id);

    @Query("DELETE FROM LinkingDoc ld WHERE ld.first = :id")
    void deleteAllByFirst(@Param("id") Long id);


//    @Query("SELECT d FROM Department d WHERE d.parentDepartmentId.depId = :parentDepartmentId")
//    Department findFirstChildDepartment(@Param("parentDepartmentId") Long parentDepartmentId);

    // @Query("SELECT d FROM Department d WHERE d.parentDepartmentId.depId = :parentDepartmentId")
    // List<Department> findFirstChildDepartment(@Param("parentDepartmentId") Long parentDepartmentId);
}
