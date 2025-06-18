package com.project.GovNetMISApplication.sendDocuments;

import com.project.GovNetMISApplication.DocReference.DocReference;
import com.project.GovNetMISApplication.Documents.Document;
import com.project.GovNetMISApplication.Documents.Enums.DocumentType;
import com.project.GovNetMISApplication.sendDocuments.Enums.Secretness;
import com.project.GovNetMISApplication.sendDocuments.Enums.documentStatus;
import com.project.GovNetMISApplication.sendDocuments.Enums.sendStatus;

import jakarta.persistence.criteria.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface SendDocumentsRepository extends JpaRepository<SendDoc,Long> {

    
    public SendDoc findBySendDocId(Long id);

//    query to retrieve all send documents that are Sent by a department
    @Query("SELECT d FROM SendDoc d JOIN FETCH d.senderDepartment sDep  WHERE sDep.depId IN :currentUserDepartmentIds")
    List<SendDoc> findSentDocumentByDepartments(@Param("currentUserDepartmentIds") List<Long> currentUserDepartmentIds);

    // query to retrieve all send documents that are received by a department
    @Query("SELECT d from SendDoc d  JOIN FETCH d.receiverDepartment rd WHERE rd.depId IN :receivedDepartments")
    List<SendDoc> findReceivedDocumentByDepartments(@Param("receivedDepartments") List<Long> receivedDepartments);

    // query to retrieve all sent documents by a department having specific document type
    @Query("SELECT d FROM SendDoc d JOIN d.senderDepartment sDep WHERE (sDep.depId IN :currentUserDepartmentIds) AND d.documentId.docType = :docType")
    List<SendDoc> findSentDocumentByDocumentType(@Param("currentUser") Long currentUser,@Param("currentUserDepartmentIds") List<Long> currentUserDepartmentIds,@Param("docType") DocumentType docType);

    // query to retrieve all Received documents by a department having specific document type
    @Query("SELECT d from SendDoc d  JOIN FETCH d.receiverDepartment rd WHERE rd.depId IN :receivedDeprtments AND d.documentId.docType = :docType")
    List<SendDoc> findReceivedDocumentByDocumentType(@Param("receivedDeprtments") List<Long> receivedDeprtments ,@Param("docType") DocumentType docType);

    // query to retrieve all sent documents by a department having specific document Status
    @Query("SELECT d FROM SendDoc d JOIN d.senderDepartment sDep WHERE (sDep.depId IN :currentUserDepartmentIds) AND d.docStatus = :docStatus")
    List<SendDoc> findSentDocumentByDocumentStatus(@Param("currentUser") Long currentUser,@Param("currentUserDepartmentIds") List<Long> currentUserDepartmentIds,@Param("docStatus") documentStatus docStatus);

    // query to retrieve all Received documents by a department having specific document Status
    @Query("SELECT d from SendDoc d  JOIN FETCH d.receiverDepartment rd WHERE rd.depId IN :receivedDeprtments AND d.docStatus = :docStatus")
    List<SendDoc> findReceivedDocumentByDocumentStatus(@Param("receivedDeprtments") List<Long> receivedDeprtments ,@Param("docStatus") documentStatus docStatus);

    // Query to retrieve sent Documents By Id Send Document id By receiver departments
    @Query("SELECT d FROM SendDoc d JOIN d.senderDepartment sDep WHERE (sDep.depId IN :currentUserDepartmentIds) AND d.sendDocId= :sendDocId")
    SendDoc findSentDocumentByIdByDepartments(@Param("currentUser") Long currentUser,@Param("currentUserDepartmentIds") List<Long> currentUserDepartmentIds,@Param("sendDocId") Long sendDocId);

    

    // Query to retrieve All sent Documents By documentId By receiver departments
    @Query("SELECT d FROM SendDoc d JOIN d.senderDepartment sDep WHERE (sDep.depId IN :currentUserDepartmentIds) AND d.documentId.docId= :docId")
    List<SendDoc> findSentDocumentByDocumentIdByDepartments(@Param("currentUser") Long currentUser,@Param("currentUserDepartmentIds") List<Long> currentUserDepartmentIds,@Param("docId") Long docId);

    @Query("SELECT d FROM SendDoc d WHERE d.documentId.docId= :docId")
    List<SendDoc> findSentDocumentByDocumentIdByDepartments(@Param("docId") Long docId);


    // query to retrieve Received documents by a department By SendDocId
    @Query("SELECT d from SendDoc d  JOIN FETCH d.receiverDepartment rd WHERE rd.depId IN :receivedDeprtments AND d.sendDocId = :sendDocId")
    SendDoc findReceivedDocumentBySendDocId(@Param("receivedDeprtments") List<Long> receivedDeprtments ,@Param("sendDocId") Long sendDocId);

    // query to retrieve all Received documents by a department having specific document Status
    @Query("SELECT d from SendDoc d  JOIN FETCH d.receiverDepartment rd WHERE rd.depId IN :receivedDeprtments AND d.sendingStatus = :sendingStatus")
    List<SendDoc> findReceivedDocumentBySendingStatus(@Param("receivedDeprtments") List<Long> receivedDeprtments ,@Param("sendingStatus") sendStatus sendingStatus);

    
    Page<SendDoc> findAll(Specification<SendDoc> spec, Pageable pageable);
    List<SendDoc> findAll(Specification<SendDoc> spec);

    // @Query("WITH RECURSIVE SendDocHierarchy AS ( "
    //     + "SELECT sendDocId, parentSendDoc FROM SendDoc WHERE sendDocId = :sendDocId "
    //     + "UNION ALL "
    //     + "SELECT d.sendDocId, d.parentSendDoc FROM SendDoc d "
    //     + "JOIN SendDocHierarchy sh ON d.parentSendDoc = sh.sendDocId ) "
    //     + "SELECT d.* "
    //     + "FROM SendDoc d JOIN SendDocHierarchy sh ON d.sendDocId = sh.sendDocId;")          
    // List<SendDoc> findAllWithChildren(@Param("sendDocId") Long sendDocId);
        
    

    // @Query(value = "WITH RECURSIVE SendDocHierarchy AS ( "
    // + "SELECT send_doc_id, parent_send_doc_id FROM send_doc WHERE send_doc_id = :sendDocId "
    // + "UNION ALL "
    // + "SELECT sd.send_doc_id, sd.parent_send_doc_id FROM send_doc sd "
    // + "JOIN SendDocHierarchy sh ON sd.parent_send_doc_id = sh.send_doc_id ) "
    // + "SELECT sd.send_doc_id "
    // + "FROM send_doc sd JOIN SendDocHierarchy sh ON sd.send_doc_id = sh.send_doc_id",
    // nativeQuery = true)
    // List<Long> findAllSendDocIds(@Param("sendDocId") Long sendDocId);

    // @Query("SELECT sd FROM SendDoc sd WHERE sd.sendDocId IN :sendDocIds")
    // List<SendDoc> findAllWithChildren(@Param("sendDocIds") List<Long> sendDocIds);

    @Query(value = "WITH RECURSIVE SendDocHierarchy AS ( "
            + "SELECT send_doc_id, parent_send_doc_id FROM send_doc WHERE send_doc_id = :sendDocId "
            + "UNION ALL "
            + "SELECT sd.send_doc_id, sd.parent_send_doc_id FROM send_doc sd "
            + "JOIN SendDocHierarchy sh ON sd.parent_send_doc_id = sh.send_doc_id "
            + "WHERE sd.parent_send_doc_id IS NOT NULL) "
            + "SELECT sh.send_doc_id FROM SendDocHierarchy sh "
            + "JOIN send_doc sd ON sh.send_doc_id = sd.send_doc_id",
            nativeQuery = true)
    List<Long> findFamilyTree(@Param("sendDocId") Long sendDocId);

    @Query(value = "WITH RECURSIVE SendDocHierarchy AS ( "
        + "SELECT send_doc_id, parent_send_doc_id FROM send_doc WHERE send_doc_id = :sendDocId "
        + "UNION ALL "
        + "SELECT sd.send_doc_id, sd.parent_send_doc_id FROM send_doc sd "
        + "JOIN SendDocHierarchy sh ON sd.send_doc_id = sh.parent_send_doc_id ) "
        + "SELECT * FROM SendDocHierarchy", nativeQuery = true)
    List<Long> findAllWithChildren(@Param("sendDocId") Long sendDocId);

    List<SendDoc> findByDocumentId(Document parent);
    
    default Specification<SendDoc> buildSpecification(sendDocCriteria criteria) {
        return (Root<SendDoc> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Add predicates based on the provided criteria

          
            // Example: Filter by subject
            if (criteria.getSubject() != null && !criteria.getSubject().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("documentId").get("subject"), "%" + criteria.getSubject() + "%"));
            }

            // Example: Filter by document id
            if (criteria.getDocumentNo() != null && !criteria.getDocumentNo().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("documentId").get("docNumber"), criteria.getDocumentNo()));
            }

            // Example: Filter by document id
            if (criteria.getDocNumber2() != null && !criteria.getDocNumber2().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("documentId").get("docNumber2"), criteria.getDocNumber2()));
            }

            // Add other criteria...

            // Example: Filter by sender department
            // if (criteria.getSenderDepartment() != null) {
            //     predicates.add(criteriaBuilder.equal(root.get("senderDepartment"), criteria.getSenderDepartment()));
            // }

            // Example: Filter by receiver department
            // if (criteria.getReceiverDepartment() != null) {
            //     predicates.add(criteriaBuilder.isMember(criteria.getReceiverDepartment(), root.get("receiverDepartment")));
            // }

            // Example: Filter by sending status
            if (criteria.getDocStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("docStatus"), criteria.getDocStatus()));
            }

            if (criteria.getSenderDepartmentIds() != null && !criteria.getSenderDepartmentIds().isEmpty()) {
                predicates.add(criteriaBuilder.in(root.get("senderDepartment").get("depId"))
                        .value(criteria.getSenderDepartmentIds()));
            }

            if (criteria.getDocReference() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("documentId").get("referenceType")
                        ,criteria.getDocReference()));
            }

            if (criteria.getReceiverDepartmentIds() != null && !criteria.getReceiverDepartmentIds().isEmpty()) {
                predicates.add(criteriaBuilder.in(root.get("receiverDepartment").get("depId"))
                        .value(criteria.getReceiverDepartmentIds()));
            }

            if (criteria.getSendingDateStart() != null && criteria.getSendingDateEnd() != null) {
                predicates.add(criteriaBuilder.between(root.get("sendingDate"),
                        criteria.getSendingDateStart(), criteria.getSendingDateEnd()));
            }

            if (criteria.getDocumentType() != null && criteria.getDocumentType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("documentId").get("docType"), criteria.getDocumentType()));
            }

            if (criteria.getExcludeSecret()) {
                predicates.add(criteriaBuilder.equal(root.get("secret"), Secretness.NON_SECRET));
            }
            // Filter by DocReference ID
            if (criteria.getReferenceId() != null) {
                Join<SendDoc, Document> documentJoin = root.join("documentId");
                Join<Document, DocReference> referenceJoin = documentJoin.join("reference");
                predicates.add(criteriaBuilder.equal(referenceJoin.get("id"), criteria.getReferenceId()));
            }

            if (criteria.getSearchTerm() != null && !criteria.getSearchTerm().isEmpty()) {
                String likeTerm = "%" + criteria.getSearchTerm() + "%";
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("documentId").get("docNumber"), likeTerm),
                        criteriaBuilder.like(root.get("documentId").get("docNumber2"), likeTerm),
                        criteriaBuilder.like(root.get("documentId").get("subject"), likeTerm),
                        criteriaBuilder.like(root.get("senderDepartment").get("depName"), likeTerm),
                        criteriaBuilder.like(root.join("receiverDepartment").get("depName"), likeTerm)
                ));
            }



            // Example: Filter by receiver department IDs
            // if (criteria.getReceiverDepartmentIds() != null && !criteria.getReceiverDepartmentIds().isEmpty()) {
            //     Join<SendDoc, Department> receiverDepartmentJoin = root.join("receiverDepartment");
            //     predicates.add(receiverDepartmentJoin.get("departmentId").in(criteria.getReceiverDepartmentIds()));
            // }


            // Combine all predicates with AND
            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

            return query.getRestriction();
        };
    }

    @Query("SELECT COUNT(sd) > 0 FROM SendDoc sd WHERE sd.documentId.docId = :documentId AND sd.receiverDepartment.depId = :receiverDepartmentId AND sd.senderDepartment.depId = :senderDepartmentId")
    boolean existsByDocumentIdAndReceiverDepartmentId(@Param("documentId") Long documentId, @Param("receiverDepartmentId") Long receiverDepartmentId, @Param("senderDepartmentId") Long senderDepartmentId);

// To determine number of  each types of sentdocuments

    @Query("SELECT d.documentId.docType, COUNT(d) FROM SendDoc d JOIN d.senderDepartment sDep WHERE (sDep.depId IN :currentUserDepartmentIds) GROUP BY d.documentId.docType")
    List<Object[]> countSentDocumentsByDepartmentsAndDocType(@Param("currentUser") Long currentUser, @Param("currentUserDepartmentIds") List<Long> currentUserDepartmentIds);
//     To determine number of each type of sentdocuments with its status
    @Query("SELECT d.documentId.docType, d.docStatus, COUNT(d) FROM SendDoc d JOIN d.senderDepartment sDep WHERE (sDep.depId IN :currentUserDepartmentIds) GROUP BY d.documentId.docType, d.docStatus")
    List<Object[]> countSentDocumentsByDepartmentsAndDocTypeAndStatus(@Param("currentUser") Long currentUser, @Param("currentUserDepartmentIds") List<Long> currentUserDepartmentIds);

    @Query("SELECT d.documentId.docType,COUNT(d) FROM SendDoc d  JOIN  d.receiverDepartment rd WHERE (rd.depId IN :receivedDepartments) GROUP BY d.documentId.docType")
    List<Object[]> findReceivedDocumentByDepartmentsByDocType(@Param("receivedDepartments") List<Long> receivedDepartments);
    @Query("SELECT d.documentId.docType, d.docStatus,COUNT(d) FROM SendDoc d  JOIN  d.receiverDepartment rd WHERE (rd.depId IN :receivedDepartments) GROUP BY d.documentId.docType,d.docStatus")
    List<Object[]> findReceivedDocumentByDepartmentsByDocTypeAndStatus(@Param("receivedDepartments") List<Long> receivedDepartments);
    // Finding Expired documents
    @Query("SELECT s FROM SendDoc s JOIN s.documentId d WHERE s.docStatus IN :statuses AND d.deadline < :currentDate AND s.senderDepartment.depId IN :currentUserDepartmentIds")
    Page<SendDoc> findAllSentDocumentsDeadlined(@Param("statuses") List<documentStatus> statuses,  @Param("currentDate") LocalDate currentDate,@Param("currentUserDepartmentIds") List<Long> currentUserDepartmentIds,Pageable pageable);

    @Query("SELECT s FROM SendDoc s JOIN s.documentId d WHERE d.docId IN :docIds AND s.receiverDepartment.depId = :departmentId")
    public List<SendDoc> findAllByDocIds(@Param("docIds") List<Long> docIds,@Param("departmentId") Long departmentId);
}