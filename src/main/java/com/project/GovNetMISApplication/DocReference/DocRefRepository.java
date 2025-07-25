package com.project.GovNetMISApplication.DocReference;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DocRefRepository extends JpaRepository<DocReference,Long>{
    Page<DocReference> findAll(Pageable pageable);
    List<DocReference> findAll();
}