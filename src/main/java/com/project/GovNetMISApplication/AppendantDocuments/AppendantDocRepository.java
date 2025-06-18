package com.project.GovNetMISApplication.AppendantDocuments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppendantDocRepository extends JpaRepository<AppendantDocs,Long>{
//    @Query("SELECT d FROM appendantDocs d where d.document.docId = :docId")
//    List<appendantDocs> findBydocumentId(@Param("docId") Long docId);

//    @Query("SELECT ad FROM appendantDocs ad JOIN ad.document d WHERE d.docId = :docId")
//    List<appendantDocs> findAppendantDocsByDocId(@Param("docId") Long docId);
//@Query("SELECT ad FROM appendantDocs ad JOIN ad.documentId d WHERE d.docId = :documentId")
//List<appendantDocs> findByDocumentId(@Param("documentId") Long documentId);

}
