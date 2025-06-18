package com.project.GovNetMISApplication.sendDocuments;

import com.project.GovNetMISApplication.Documents.Document;
import com.project.GovNetMISApplication.Documents.DocumentRepository;
import com.project.GovNetMISApplication.Documents.Enums.DocumentType;
import com.project.GovNetMISApplication.Documents.Enums.ReferenceTypes;
import com.project.GovNetMISApplication.Exceptions.MyNotFoundException;
import com.project.GovNetMISApplication.LinkingDocs.LinkDocDTO;
import com.project.GovNetMISApplication.LinkingDocs.LinkingDocService;
import com.project.GovNetMISApplication.sendDocuments.Enums.documentStatus;
import com.project.GovNetMISApplication.sendDocuments.Enums.sendStatus;
import com.project.GovNetMISApplication.user.CurrentUserInfoService;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sendDocument")
public class SendDocumentController {
    Logger log=LoggerFactory.getLogger(SendDocumentController.class);

    @Autowired
    private SendDocumentService sendDocumentSer;
    @Autowired
    private LinkingDocService linkingDocService;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private CurrentUserInfoService currentUserInfoSer;
    @PostMapping("/add")
    public ResponseEntity<String> addSendDocument(@Valid @RequestBody SendDocumentRequestDTO sendDocument){
        sendDocumentSer.addSendDocuments(sendDocument);
        return ResponseEntity.ok("Document sent successfully");
    }
    @PutMapping("/update/{sendDocId}")
    public String updateSendDoc(@PathVariable Long sendDocId,
                                @RequestBody SendDocumentRequestDTO sendDocumentRequestDTO
                                ){
        sendDocumentSer.updateSendDocument(sendDocId,sendDocumentRequestDTO);
        return "sent document updated successfully";
    }
    @GetMapping("/getAllSentDocumentsByDepartment")
    public List<SendDocResponseDTO> allSentDocs(){
        return sendDocumentSer.getAllSentDocsByDepartment();
    }
    @GetMapping("/getAllReceivedDocumentsByDepartment")
    public List<SendDocResponseDTO> getReceivedDocuments(){
        return sendDocumentSer.getDocumentsReceivedByDepartments();
    }
    @GetMapping("/getSentDocumentsByDocumentType/{docType}")
    public List<SendDocResponseDTO> getByDocType(@PathVariable DocumentType docType){
        return sendDocumentSer.getDocumentsByType(docType);
    }
    @GetMapping("/getReceivedDocumentsByDocumentType/{docType}")
    public List<SendDocResponseDTO> getReceivedDocsByType(@PathVariable DocumentType docType){
        return sendDocumentSer.getReceivedDocsByDocType(docType);
    }
    @GetMapping("/getSentDocumentsByDocumentStatus/{docStatus}")
    public List<SendDocResponseDTO> getSentDocByStatus(@PathVariable documentStatus docStatus){
      return sendDocumentSer.getSentDocsByStatus(docStatus);
    }
    @GetMapping("/getReceivedDocumentsByDocumentStatus/{docStatus}")
    public List<SendDocResponseDTO> getDocsByDocStatus(@PathVariable documentStatus docStatus){
      return sendDocumentSer.getReceivedDocsByStatus(docStatus);

    }
    @GetMapping("/getReceivedDocumentsBySendingStatus/{sendingStatus}")
    public List<SendDocResponseDTO> getDocsBySendingStatus(@PathVariable sendStatus sendingStatus){
       return sendDocumentSer.getReceivedDocsBySendingStatus(sendingStatus);

    }
    @PutMapping("/{sendDocId}")
    public ResponseEntity<String> updateDocumentStatus(
            @PathVariable Long sendDocId,
            @RequestBody  UpdateDocumentStatusRequest request
    ){
        sendDocumentSer.updateDocumentStatus(sendDocId,request.getDocStatus());
        return ResponseEntity.ok("Document status updated successfully");
    }
    @GetMapping("/getSentDocbyId/{sendDocId}")
    public SendDoc getSendDocById(@PathVariable Long sendDocId){
        return sendDocumentSer.getSendDocById(sendDocId);
    }
    @GetMapping("/getSentDocumentbyId/{sendDocId}")
    public SendDoc getSendDocumentById(@PathVariable Long sendDocId){
        return sendDocumentSer.getSendDocumentById(sendDocId);
    }

    @GetMapping("/getReceivedDocBySendDocId/{sendDocId}")
    public SendDoc getReceivedDocById(@PathVariable Long sendDocId){
        return sendDocumentSer.getReceivedDocBySendDocId(sendDocId);
    }

    @GetMapping("/byDocId/{docId}")
    public List<SendDocResponseDTO> getSentDocsByDocId(@PathVariable Long docId){
        return sendDocumentSer.getAllSentDocByDocId1(docId);
    }
    @GetMapping("/getReceivedDocsByDocId/{docId}")
    public List<SendDoc> getReceivedDocsByDocId(@PathVariable Long docId){
        return sendDocumentSer.getReceivedDocsByDocId(docId);
    }
    @GetMapping("/getSendDocTree/{sendDocId}")
    public List<SendDocResponseDTO> getSendDocTree(@PathVariable Long sendDocId){
        return sendDocumentSer.getsendDocTree(sendDocId);
    }
    @GetMapping("/filter")
    public Page<SendDocResponseDTO> filterSendDocs(
            Pageable pageable,
            @RequestParam(required = false) String documentNo,
            @RequestParam(required = false) String docNumber2,
            @RequestParam(required = false) DocumentType documentType,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) List<Long> senderDepartmentIds,
            @RequestParam(required = false) List<Long> receiverDepartmentIds,
            @RequestParam(required = false) documentStatus documentStatus,
            @RequestParam(required = false) LocalDate sendingDateStart,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) ReferenceTypes referenceType,
            @RequestParam(required = false) Long referenceId,
            @RequestParam(required = false) LocalDate sendingDateEnd) {

        sendDocCriteria criteria = new sendDocCriteria();
        criteria.setDocumentNo(documentNo);
        criteria.setDocNumber2(docNumber2);
        criteria.setDocumentType(documentType);
        criteria.setSubject(subject);
        criteria.setSearchTerm(searchTerm);
        criteria.setReferenceId(referenceId);
        
        // if (senderDepartmentIds == null) {
        //     criteria.setSenderDepartmentIds(getAuth());
        // }else{
        //     criteria.setSenderDepartmentIds(senderDepartmentIds);
        // }
        if (referenceType != null) {
            criteria.setDocReference(referenceType);
        }
        if (docNumber2 != null) {
            criteria.setDocNumber2(docNumber2);
        }
        if (receiverDepartmentIds == null) {
            criteria.setReceiverDepartmentIds(currentUserInfoSer.getCurrentUserDepartmentIds());
        }else{
            criteria.setReceiverDepartmentIds(receiverDepartmentIds);
        }
        criteria.setDocStatus(documentStatus);
        criteria.setSendingDateStart(sendingDateStart);
        criteria.setSendingDateEnd(sendingDateEnd);
        if (currentUserInfoSer.getCurrentUser()!=null) {
            criteria.setExcludeSecret(!currentUserInfoSer.hasSecretAccess());
        }
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("sendDocId").descending()
        );
         return sendDocumentSer.findByCriteria(criteria, sortedPageable);
    }

    @GetMapping("/filter1")
    public Page<SendDocResponseDTO> filterSendedSendDocs(
            Pageable pageable,
            @RequestParam(required = false) String documentNo,
            @RequestParam(required = false) String documentNo2,
            @RequestParam(required = false) DocumentType documentType,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) List<Long> senderDepartmentIds,
            @RequestParam(required = false) List<Long> receiverDepartmentIds,
            @RequestParam(required = false) documentStatus documentStatus,
            @RequestParam(required = false) LocalDate sendingDateStart,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) ReferenceTypes referenceType,
            @RequestParam(required = false) Long referenceId,
            @RequestParam(required = false) LocalDate sendingDateEnd) {

        sendDocCriteria criteria = new sendDocCriteria();
        criteria.setDocumentNo(documentNo);
        criteria.setDocumentType(documentType);
        criteria.setSubject(subject);
        criteria.setSearchTerm(searchTerm);
        criteria.setReferenceId(referenceId);
        
        // if (receiverDepartmentIds == null) {
        //     criteria.setReceiverDepartmentIds(currentUserInfoSer.getCurrentUserDepartmentIds());
        // }else{
        //     criteria.setReceiverDepartmentIds(receiverDepartmentIds);
        // }
        if (documentNo2 != null) {
            criteria.setDocNumber2(documentNo2);
        }
        if (senderDepartmentIds == null) {
            criteria.setSenderDepartmentIds(currentUserInfoSer.getCurrentUserDepartmentIds());
        }else{
            criteria.setSenderDepartmentIds(senderDepartmentIds);
        }
        if (referenceType != null) {
            criteria.setDocReference(referenceType);
        }
        criteria.setDocStatus(documentStatus);
        criteria.setSendingDateStart(sendingDateStart);
        criteria.setSendingDateEnd(sendingDateEnd);
        if (currentUserInfoSer.getCurrentUser()!=null) {
            criteria.setExcludeSecret(!currentUserInfoSer.hasSecretAccess());
        }
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("sendDocId").descending()
        );
        return sendDocumentSer.findByCriteria(criteria, sortedPageable);
        
        // return sendDocumentSer.findByCriteria(criteria);
        // return new ResponseEntity<>(filteredDocs, HttpStatus.OK);
    }

    @GetMapping("/filter2")
    public List<SenDocReportDTO> filterReceivedDocsWithoughtPagination(
            @RequestParam(required = false) String documentNo,
            @RequestParam(required = false) String documentNo2,
            @RequestParam(required = false) DocumentType documentType,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) List<Long> senderDepartmentIds,
            @RequestParam(required = false) List<Long> receiverDepartmentIds,
            @RequestParam(required = false) documentStatus documentStatus,
            @RequestParam(required = false) LocalDate sendingDateStart,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) ReferenceTypes referenceType,
            @RequestParam(required = false) Long referenceId,
            @RequestParam(required = false) LocalDate sendingDateEnd) {

        sendDocCriteria criteria = new sendDocCriteria();
        criteria.setDocumentNo(documentNo);
        criteria.setDocumentType(documentType);
        criteria.setSubject(subject);
        criteria.setSearchTerm(searchTerm);
        criteria.setReferenceId(referenceId);
        if (documentNo2 != null) {
            criteria.setDocNumber2(documentNo2);
        }
        if (receiverDepartmentIds == null) {
            criteria.setReceiverDepartmentIds(currentUserInfoSer.getCurrentUserDepartmentIds());
        }else{
            criteria.setReceiverDepartmentIds(receiverDepartmentIds);
        }
        if (referenceType != null) {
            criteria.setDocReference(referenceType);
        }
        criteria.setDocStatus(documentStatus);
        criteria.setSendingDateStart(sendingDateStart);
        criteria.setSendingDateEnd(sendingDateEnd);
        if (currentUserInfoSer.getCurrentUser()!=null) {
            criteria.setExcludeSecret(!currentUserInfoSer.hasSecretAccess());
        }
        return sendDocumentSer.findByCriteria(criteria);
    }
    @GetMapping("/filter3")
    public List<SenDocReportDTO> filterSentDocsWithoughtPagination(
            @RequestParam(required = false) String documentNo,
            @RequestParam(required = false) String documentNo2,
            @RequestParam(required = false) DocumentType documentType,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) List<Long> senderDepartmentIds,
            @RequestParam(required = false) List<Long> receiverDepartmentIds,
            @RequestParam(required = false) documentStatus documentStatus,
            @RequestParam(required = false) LocalDate sendingDateStart,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) ReferenceTypes referenceType,
            @RequestParam(required = false) Long referenceId,
            @RequestParam(required = false) LocalDate sendingDateEnd) {

        sendDocCriteria criteria = new sendDocCriteria();
        criteria.setDocumentNo(documentNo);
        criteria.setDocumentType(documentType);
        criteria.setSubject(subject);
        criteria.setSearchTerm(searchTerm);
        criteria.setReferenceId(referenceId);
        if (documentNo2 != null) {
            criteria.setDocNumber2(documentNo2);
        }
        if (referenceType != null) {
            criteria.setDocReference(referenceType);
        }
        if (senderDepartmentIds == null) {
            criteria.setSenderDepartmentIds(currentUserInfoSer.getCurrentUserDepartmentIds());
        }else{
            criteria.setSenderDepartmentIds(senderDepartmentIds);
        }
        criteria.setDocStatus(documentStatus);
        criteria.setSendingDateStart(sendingDateStart);
        criteria.setSendingDateEnd(sendingDateEnd);
        if (currentUserInfoSer.getCurrentUser()!=null) {
            criteria.setExcludeSecret(!currentUserInfoSer.hasSecretAccess());
        }
        return sendDocumentSer.findByCriteria(criteria);
    }


    public static LocalDateTime formatDate(String frontEndDate) {
        LocalDate date = LocalDate.parse(frontEndDate);
        LocalDateTime dateTime = LocalDateTime.of(date, LocalDateTime.MIN.toLocalTime());
        return dateTime;
    }
    @GetMapping("/ExpiredDocuments")
    public Page<SendDocResponseDTO> AllExpiredDocuments(Pageable pageable){
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("sendDocId").descending()
        );
        Page<SendDocResponseDTO> allDeadlinedDocuments = sendDocumentSer.getAllDeadlinedDocuments(sortedPageable);
        return allDeadlinedDocuments;
    }

    @GetMapping("/linkings/{documentId}")
    public List<LinkDocDTO> LinkedDocs( @PathVariable Long documentId){
        log.debug("the id of the document is: "+documentId);
        Document doc = documentRepository.findById(documentId).orElseThrow(()-> new MyNotFoundException("The document is not found!"));
        doc.getDepartment().getDepId();
        List<Long> department = currentUserInfoSer.getCurrentUserDepartmentIds();
        if (department.contains(doc.getDepartment().getDepId())) {
            return linkingDocService.findlinkedDocs(documentId);
        }
        return linkingDocService.findDocFromSenDoc(documentId);
    }

}
