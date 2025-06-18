package com.project.GovNetMISApplication.Documents;

import com.project.GovNetMISApplication.Departments.APIResponse;
import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.Documents.Enums.DocumentType;
import com.project.GovNetMISApplication.Documents.Enums.ReferenceTypes;
import com.project.GovNetMISApplication.LinkingDocs.LinkDocDTO;
import com.project.GovNetMISApplication.LinkingDocs.LinkingDocService;
import com.project.GovNetMISApplication.UploadAndDownload.DocDownloadService;

import com.project.GovNetMISApplication.user.CurrentUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {
    Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentSer;

    @Value("${file.upload-dir}")
    private String EXTERNAL_FILE_PATH;
    @Autowired
    DocDownloadService fileDownloadService;
    @Autowired
    private LinkingDocService linkingDocService;
    @Autowired
    private CurrentUserInfoService currentUserInfoService;

    @PostMapping("/add")
    public String addDocument(@ModelAttribute("document1") DocumentDTO document1,
            @RequestParam("mainDocument") MultipartFile mainDocument,
            @RequestParam(value = "appendantDocuments", required = false) List<MultipartFile> appendantDocuments)
            throws IOException {

        documentSer.addDocument(document1);
        return "document added successfully";
    }

    @GetMapping("/getByDepartment")
    public Page<DocumentResponseDTO> listAllDocuments(Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("docId").descending());
        return documentSer.getDocumentsOfDepartmentForLoggedInUser(sortedPageable);
    }
    // ole one
    // @GetMapping("/getByDepartment")
    // public APIResponse<Page<DocumentResponseDTO>>
    // listAllDocuments(@RequestParam(required = false) Integer page,
    // @RequestParam(required = false) Integer pageSize){

    // Page<DocumentResponseDTO> documentsOfDepartmentForLoggedInUser =
    // documentSer.getDocumentsOfDepartmentForLoggedInUser(page, pageSize);
    // Long totalRecords = documentSer.countDocumentOfCurrentUserDepartment();
    // return new
    // APIResponse<>(documentsOfDepartmentForLoggedInUser.getSize(),totalRecords,documentsOfDepartmentForLoggedInUser);
    // }

    @GetMapping("/getOwnDocuments")
    public APIResponse<Page<DocumentResponseDTO>> getDocumentsOfLogedInUser(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {

        Page<DocumentResponseDTO> documentsOfUser = documentSer.getDocumentsOfUser(page, pageSize);
        Long totalRecords = documentSer.countDocumentOfCurrentUser();
        return new APIResponse<>(documentsOfUser.getSize(), totalRecords, documentsOfUser);
    }

    @DeleteMapping("/delete/{docId}")
    public String deleteDocument(@PathVariable Long docId) {
        // documentRepository.deleteById(docId);
        documentSer.deleteDocument(docId);
        return "Document deleted successfully";
    }

    @GetMapping("/getDocumentById/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable Long id) {
        Document docById = documentSer.getDocumentById(id);
        return ResponseEntity.ok(docById);
    }

    @PutMapping("/updateDocument/{id}")
    public ResponseEntity<String> updateDocumentPartially(@PathVariable Long id,
            @ModelAttribute("documentDTO") DocumentDTO documentDTO) throws IOException {
        Document document = documentSer.updateDocumentPartially(id, documentDTO);
        return ResponseEntity.ok("document updated successfully");
    }

    @GetMapping("/getByDocumentType/{docType}")
    public Page<DocumentResponseDTO> getAllByDocType(@PathVariable DocumentType docType, Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("docId").descending());
        return documentSer.getByDocType(docType, sortedPageable);
    }

    @PostMapping("/addAppendantDoc/{docId}")
    public ResponseEntity<String> addAppendantDocument(@PathVariable Long docId,
            @RequestParam("file") MultipartFile file) throws IOException {
        documentSer.addNewAppendantDocument(docId, file);
        return ResponseEntity.ok("new appendant document added successfully");
    }

    @DeleteMapping("/removeAppendantDoc/{docId}/{appendantDocId}")
    public ResponseEntity<String> deleteAppendantDocFromDocList(@PathVariable Long docId,
            @PathVariable Long appendantDocId) {
        documentSer.removeAppendantDoc(docId, appendantDocId);
        return ResponseEntity.ok("appendant document remove successfully");
    }

    @GetMapping("/filter")
    public Page<DocumentResponseDTO> findDocuments(
            @RequestParam(required = false) String docNumber,
            @RequestParam(required = false) String docNumber2,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) DocumentType docType,
            @RequestParam(required = false) ReferenceTypes referenceType,
            @RequestParam(required = false) Long referenceId,
            @RequestParam(required = false) LocalDate documentCreationStartDate,
            @RequestParam(required = false) LocalDate documentCreationEndDate,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) List<Department> department,
            Pageable pageable) {

        DocumentCriteria documentCriteria = new DocumentCriteria();
        documentCriteria.setDocNumber(docNumber);
        documentCriteria.setDocNumber2(docNumber2);
        documentCriteria.setSubject(subject);
        documentCriteria.setDocType(docType);
        documentCriteria.setReferenceType(referenceType);
        documentCriteria.setReferenceId(referenceId);
        documentCriteria.setDocumentCreationStartDate(documentCreationStartDate);
        documentCriteria.setDocumentCreationEndDate(documentCreationEndDate);
        documentCriteria.setSearchTerm(searchTerm);
        documentCriteria.setDepartment(currentUserInfoService.getCurrentUserDepartments());

        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("docId").descending());
        Page<DocumentResponseDTO> documentsOfDepartment = documentSer.getFilteredDocuments(documentCriteria,
                sortedPageable);
        return documentsOfDepartment;
    }

    @GetMapping("/forlinking")
    public List<DocForLinkingDTO> getforlinking(
            @RequestParam(required = false) String searchterm) {
        return documentSer.getforlinking(searchterm);
    }

    @GetMapping("/linkings/{documentId}")
    public List<LinkDocDTO> LinkedDocs(@PathVariable Long documentId) {
        log.debug("the id of the document is: " + documentId);
        return linkingDocService.findlinkedDocs(documentId);
    }

}
