package com.project.GovNetMISApplication.Backup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbBackupRepository extends JpaRepository<BackupDB,Long> {
  
}
