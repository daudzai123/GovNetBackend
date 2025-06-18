package com.project.DocumentMIS.DocumentReport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocReportRepository extends JpaRepository<DocReport,Long> {
    @Query("SELECT dr FROM DocReport dr where dr.sendDoc.sendDocId = :sendDocId")
    public List<DocReport> findBysendDocId(Long sendDocId);

}
