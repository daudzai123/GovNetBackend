package com.project.DocumentMIS.Documents;

import com.project.DocumentMIS.Departments.Department;
import com.project.DocumentMIS.DocReference.DocReference;
import com.project.DocumentMIS.Documents.Enums.DocumentType;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    // find all documents for a user that belongs to the user`s departments
    @Query("SELECT d FROM Document d JOIN d.userId u JOIN u.department dep WHERE u.id = :userId OR dep.depId IN :departmentIds")
    List<Document> findDocumentsForUserDepartments(@Param("userId") Long userId,
            @Param("departmentIds") List<Long> departmentIds);

    // find all documents for a user that belongs to the user`s departments
    @Query("SELECT d FROM Document d JOIN d.userId u JOIN u.department dep WHERE u.id = :userId OR dep.depId IN :departmentIds")
    List<Document> findDocumentsForUserDepartmentsWithPagination(@Param("userId") Long userId,
            @Param("departmentIds") List<Long> departmentIds, PageRequest pageRequest);

    @Query("SELECT d FROM Document d JOIN d.userId u JOIN d.department dep WHERE dep.depId IN :departmentIds")
    Page<Document> findDocumentsForUserDepartmentsWithPagination1(@Param("departmentIds") List<Long> departmentIds,
            Pageable pageable);

    // find all document for a user that was created by this user
    @Query("SELECT d FROM Document d WHERE d.userId.id = :userId")
    List<Document> findByUserId(@Param("userId") Long userId);

    @Query("SELECT d FROM Document d WHERE d.userId.id = :userId")
    List<Document> findByUserIdWithPagination(@Param("userId") Long userId, PageRequest pageRequest);

    @Query("SELECT d FROM Document d JOIN d.userId u JOIN u.department dep WHERE (u.id = :userId OR dep.depId IN :departmentIds) AND d.docType= :documentType")
    List<Document> findByDocumentType(@Param("userId") Long userId, @Param("departmentIds") List<Long> departmentIds,
            @Param("documentType") DocumentType documentType);

    @Query("SELECT COUNT(d) FROM Document d JOIN d.userId u JOIN u.department dep WHERE u.id = :userId OR dep.depId IN :departmentIds")
    Long CountNumberOfDocumentsOfCurrentUserDepartment(Long userId, List<Long> departmentIds);

    @Query("SELECT COUNT(d) FROM Document d WHERE d.userId.id = :currentUserId")
    Long CountNumberOfDocumentsOfCurrentUser(Long currentUserId);

    // Find documents of department filter by document type
    @Query("SELECT d FROM Document d JOIN d.department dep WHERE (dep.depId IN :departmentIds) AND d.docType= :documentType")
    Page<Document> findByDocumentTypeWithPagination(List<Long> departmentIds, DocumentType documentType,
            Pageable pageable);

    // @Query("SELECT d FROM Document d JOIN d.userId u JOIN u.department dep WHERE
    // (u.id = :userId OR dep.depId IN :departmentIds) AND d.docType=
    // :documentType")
    // List<Document> findByDocumentTypeWithPagination(Long userId, List<Long>
    // departmentIds, DocumentType documentType, PageRequest of);

    @Query("SELECT COUNT(d) FROM Document d JOIN d.department dep WHERE (dep.depId IN :departmentIds) AND d.docType= :documentType")
    Long countDocumentOfCurrentUserDepartmentByDocType(List<Department> departmentIds, DocumentType documentType);

    @Query("SELECT d FROM Document d JOIN d.department dep " +
            "LEFT JOIN d.reference ref WHERE " +
            "(:docNumber IS NULL OR d.docNumber = :docNumber) AND " +
            "(:docType IS NULL OR d.docType = :docType) AND " +
            "(:subject IS NULL OR d.subject = :subject) AND " +
            "(:name IS NULL OR ref.id = :name) AND " +
            "(dep.depId IN :currentUserDepartmentIds)")
    Page<Document> findDocumentsOfDepartment(
            @Param("currentUserDepartmentIds") List<Long> currentUserDepartmentIds,
            @Param("docNumber") String docNumber,
            @Param("docType") DocumentType docType,
            @Param("subject") String subject,
            @Param("name") Long name,
            Pageable pageable);

    @Query("SELECT d FROM Document d JOIN d.department dep WHERE " +
            "(:searchterm IS NULL OR d.docNumber LIKE %:searchterm%) Or " +
            "(:searchterm IS NULL OR d.subject LIKE %:searchterm%) AND " +
            "(dep.depId IN :currentUserDepartmentIds)")
    List<Document> findbycriteria(
            @Param("currentUserDepartmentIds") List<Long> currentUserDepartmentIds,
            @Param("searchterm") String searchterm);

    @Query("DELETE AppendantDocs ")
    void deleteAppendantDocsByDocument(Document document);

    @Modifying
    @Query("DELETE FROM Document d WHERE d = :document")
    void deleteDocumentAndSendDocs(Document document);


    // Custom method to find documents based on criteria
    default Page<Document> findFilteredDocuments(DocumentCriteria criteria, Pageable pageable) {
        Specification<Document> spec = buildSpecification(criteria);
        return findAll(spec, pageable);
    }

    Page<Document> findAll(Specification<Document> spec, Pageable pageable);

    // Specification builder method
    default Specification<Document> buildSpecification(DocumentCriteria criteria) {
        return (Root<Document> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by docNumber

            if (criteria.getDocNumber() != null && !criteria.getDocNumber().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("docNumber"), criteria.getDocNumber()));
            }

            // Filter by docNumber2
            if (criteria.getDocNumber2() != null && !criteria.getDocNumber2().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("docNumber2"), criteria.getDocNumber2()));
            }


            // Filter by subject
            if (criteria.getSubject() != null && !criteria.getSubject().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("subject"), "%" + criteria.getSubject() + "%"));
            }

            // Filter by ImplementationReferenceId
            if (criteria.getReferenceId() != null) {
                Join<Document, DocReference> referenceJoin = root.join("reference", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(referenceJoin.get("id"), criteria.getReferenceId()));
            }

            // Filter by referenceType
            if (criteria.getReferenceType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("referenceType"), criteria.getReferenceType()));
            }

            // Filter by docType
            if (criteria.getDocType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("docType"), criteria.getDocType()));
            }

            // Filter by documentCreationStartDate and documentCreationEndDate
            if (criteria.getDocumentCreationStartDate() != null && criteria.getDocumentCreationEndDate() != null) {
                predicates.add(criteriaBuilder.between(root.get("creationDate"), criteria.getDocumentCreationStartDate(), criteria.getDocumentCreationEndDate()));
            }
            // Filter by department
            if (criteria.getDepartment() != null && !criteria.getDepartment().isEmpty()) {
                Join<Document, Department> departmentJoin = root.join("department", JoinType.INNER);
                predicates.add(departmentJoin.in(criteria.getDepartment()));
            }

            // Filter by searchTerm
            if (criteria.getSearchTerm() != null && !criteria.getSearchTerm().isEmpty()) {
                String likeTerm = "%" + criteria.getSearchTerm() + "%";
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.equal(root.get("docNumber"), likeTerm),
                        criteriaBuilder.equal(root.get("docNumber2"), likeTerm),
                        criteriaBuilder.like(root.get("docName"), likeTerm),
                        criteriaBuilder.like(root.get("subject"), likeTerm)
                ));
            }

            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            return query.getRestriction();
        };
    }

}
