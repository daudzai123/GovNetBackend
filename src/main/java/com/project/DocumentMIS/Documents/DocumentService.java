package com.project.DocumentMIS.Documents;

import com.project.DocumentMIS.ActivitiesLog.ActivityLog;
import com.project.DocumentMIS.ActivitiesLog.ActivityLogService;
import com.project.DocumentMIS.AppendantDocuments.AppendantDocRepository;
import com.project.DocumentMIS.AppendantDocuments.AppendantDocs;
import com.project.DocumentMIS.Departments.Department;
import com.project.DocumentMIS.Departments.DepartmentRepository;
import com.project.DocumentMIS.DocReference.DocRefRepository;
import com.project.DocumentMIS.DocReference.DocReference;
import com.project.DocumentMIS.Documents.Enums.DocumentType;
import com.project.DocumentMIS.Documents.Enums.ReferenceTypes;
import com.project.DocumentMIS.Exceptions.MyNotFoundException;
import com.project.DocumentMIS.LinkingDocs.LinkingDoc;
import com.project.DocumentMIS.LinkingDocs.LinkingDocService;
import com.project.DocumentMIS.Notifications.Notification;
import com.project.DocumentMIS.Notifications.NotificationRepository;
import com.project.DocumentMIS.sendDocuments.SendDoc;
import com.project.DocumentMIS.sendDocuments.SendDocumentService;
import com.project.DocumentMIS.sendDocuments.SendDocumentsRepository;
import com.project.DocumentMIS.services.FileUploadService;
import com.project.DocumentMIS.user.CurrentUserInfoService;
import com.project.DocumentMIS.user.Users;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Paths.get;

@Service
public class DocumentService {

    Logger log = LoggerFactory.getLogger(DocumentService.class);

    @Value("${file.upload-dir}")
    private String UPLOAD_DIRECTORY;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private DocumentRepository documentRepo;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private AppendantDocRepository appendantDocRepo;
    @Autowired
    private PathToDownloadUrlService pathToDownloadUrlService;
    @Autowired
    private CurrentUserInfoService currentUserInfo;
    @Autowired
    private SendDocumentsRepository sendDocumentsRepository;
    @Autowired
    private ActivityLogService activityLogService;
    @Autowired
    private DocRefRepository docRefRepository;
    @Autowired
    private SendDocumentService sendDocumentService;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private LinkingDocService linkingDocService;

    @Transactional
    public void addDocument(DocumentDTO documentdto) throws IOException {
        Document document1=new Document();
        MultipartFile mainDoc = documentdto.getMainDocument();
        String uniqueFileName = generateUniqueFileName(mainDoc.getOriginalFilename(), UPLOAD_DIRECTORY);
        String filePath = UPLOAD_DIRECTORY + uniqueFileName;
        Path path = Paths.get(filePath);
        Files.write(path, mainDoc.getBytes());

        // working with appendant documents
        Department creatorDep = null;
        if (documentdto.getDepartment() != null) {
            creatorDep = departmentRepository.findById(documentdto.getDepartment()).get();
            document1.setDepartment(creatorDep);
        } else {
            creatorDep = currentUserInfo.getCurrentUserDepartments().get(0);
            document1.setDepartment(creatorDep);
        }

        List<MultipartFile> appendantDocuments = documentdto.getAppendantDocuments();
        List<AppendantDocs> newAppendantDocsList = new ArrayList<>();
        if (appendantDocuments != null && !appendantDocuments.isEmpty()) {
            for (int i = 0; i < appendantDocuments.size(); i++) {
                MultipartFile file = appendantDocuments.get(i);
                // appendantDocs appendantDocs1=appendantDocsList.get(i);
                String uniqueAppendantDocName = generateUniqueFileName(file.getOriginalFilename(), UPLOAD_DIRECTORY);
                String appendantDocPath = UPLOAD_DIRECTORY + uniqueAppendantDocName;

                Path appendantDocumentPath = Paths.get(appendantDocPath);
                Files.write(appendantDocumentPath, file.getBytes());

                AppendantDocs appendantDoc1 = new AppendantDocs();
                appendantDoc1.setAppendantDocPath(pathToDownloadUrlService.getAccessUrl(uniqueAppendantDocName));
                appendantDoc1.setAppendantDocName(file.getOriginalFilename());
                appendantDoc1
                        .setAppendantDocDownloadUrl(pathToDownloadUrlService.getDownloadUrl(uniqueAppendantDocName));
                newAppendantDocsList.add(appendantDoc1);
                // AppendantDocs save = appendantDocRepo.save(appendantDoc1);
                newAppendantDocsList.add(appendantDoc1);
            }
        }
        appendantDocRepo.saveAll(newAppendantDocsList);
        document1 = mapper.map(documentdto, Document.class);
        document1.setDepartment(creatorDep);
        if (documentdto.getReferenceType() != null) {
            document1.setReferenceType(documentdto.getReferenceType());
        }
        // if (documentdto.getDocType().equals(DocumentType.FARMAN)) {
        // document1.setReferenceType(ReferenceTypes.AMIR);
        // }
        // else {
        // document1.setReferenceType(ReferenceTypes.KABINA);
        // }
        document1.setUserId(currentUserInfo.getCurrentUser());
        document1.setDocName(mainDoc.getOriginalFilename());
        document1.setAppendantDocsList(newAppendantDocsList);
        document1.setCreationDate(LocalDate.now());
        if (documentdto.getReference() != null) {
            DocReference docReference = docRefRepository.findById(documentdto.getReference()).orElseThrow();
            document1.setReference(docReference);
        }
        document1.setDocPath(pathToDownloadUrlService.getAccessUrl(uniqueFileName));
        document1.setDownloadUrl(pathToDownloadUrlService.getDownloadUrl(uniqueFileName));
        Document save = documentRepo.save(document1);
        if (documentdto.getLinkingDocs() != null && documentdto.getLinkingDocs().size() > 0) {
            for (Long linkDoc : documentdto.getLinkingDocs()) {
                linkingDocService.linkDocuments(save.getDocId(), linkDoc);
            }
        }
        Map<String, Object> details = new HashMap<>();
        details.put("document Number ", save.getDocNumber());
        details.put("document subject ", save.getSubject());
        details.put("document type ", save.getDocType());
        ActivityLog activityLog = new ActivityLog();
        String content = activityLog.MapToString(details);
        // appendantDocRepo.saveAll(newAppendantDocsList);
        List<Department> department = save.getUserId().getDepartment();
        activityLogService.logsActivity("Document", document1.getDocId(), "created", department, content);
    }

    public Document getDocumentById(Long id) {
        Document document = documentRepo.findById(id)
                .orElseThrow(() -> new MyNotFoundException("Document with id " + id + " not found"));
        return document;
    }

    public Optional<Document> getSingleDocument(Long id) {

        Optional<Document> byId = documentRepo.findById(id);
        if (byId.isEmpty()) {
            throw new MyNotFoundException("Document with id " + id + " not found");
        }
        return byId;
    }

    public DocumentResponseDTO getDocById(Long id) {
        Document document = documentRepo.findById(id)
                .orElseThrow(() -> new MyNotFoundException("Document with id " + id + " not found"));
        DocumentResponseDTO documentResponseDTO = mapEntityToDTO(document);
        return documentResponseDTO;
    }

    public Page<DocumentResponseDTO> getDocumentsOfDepartmentForLoggedInUser(Pageable pageable) {
        Long currentUserId = currentUserInfo.getCurrentUserId();
        List<Long> currentUserDepartmentIds = currentUserInfo.getCurrentUserDepartmentIds();
        if (currentUserDepartmentIds == null || currentUserDepartmentIds.isEmpty()) {
            return null;
        }

        Page<Document> documentList = null;
        if (currentUserId != null) {
            documentList = documentRepo.findDocumentsForUserDepartmentsWithPagination1(
                    currentUserDepartmentIds, pageable);
        }

        List<DocumentResponseDTO> documentResponseDTO = documentList.stream().map(document -> {
            DocumentResponseDTO map = mapper.map(document, DocumentResponseDTO.class);
            map.setUserId(document.getUserId().getId());
            if (document.getReference() != null) {
                map.setImpReference(document.getReference().getRefName());
            }
            if (document.getReference() != null) {
                map.setImpReference(document.getReference().getRefName());
            }
            // map.setDepartment(document.getDepartment());
            map.setFirstName(document.getUserId().getFirstName());
            List<String> appendantDocPathList = new ArrayList<>();
            List<String> appendantDocDownloadURLList = new ArrayList<>();
            for (int i = 0; i < document.getAppendantDocsList().size(); i++) {
                String appendantDocPath = document.getAppendantDocsList().get(i).getAppendantDocPath();
                appendantDocPathList.add(appendantDocPath);
                String appendantDocDownloadUrl = document.getAppendantDocsList().get(i).getAppendantDocDownloadUrl();
                appendantDocDownloadURLList.add(appendantDocDownloadUrl);
            }
            map.setAppendantDocPath(appendantDocPathList);
            map.setAppendantDocDownloadUrl(appendantDocDownloadURLList);
            map.setDepName(document.getDepartment().getDepName());
            if (document.getDocType() != null) {
                map.setDocType(document.getDocType());
            }
            if (document.getReference() != null) {
                map.setImpReference(document.getReference().getRefName());
            }
            if (document.getReferenceType() != null){
                map.setReferenceType(document.getReferenceType());
            }
            return map;
        }).collect(Collectors.toList());
        return new PageImpl<>(documentResponseDTO, pageable, documentList.getTotalElements());
    }

    public Page<DocumentResponseDTO> getDocumentsOfUser(Integer page, Integer pageSize) {
        Long currentUserId = currentUserInfo.getCurrentUserId();
        List<Document> documentList = null;
        if (page != null && pageSize != null) {
            page = page - 1;
            if (page < 0) {
                String errorMessage = "Invalid pageNumber. Please provide a pageNumber greater than zero.";
                throw new IllegalArgumentException(errorMessage);
            }
            documentList = documentRepo.findByUserIdWithPagination(currentUserId, PageRequest.of(page, pageSize));
        } else {
            documentList = documentRepo.findByUserId(currentUserId);
        }
        List<DocumentResponseDTO> collect = documentList.stream().map(document -> {
            DocumentResponseDTO map = mapper.map(document, DocumentResponseDTO.class);
            map.setUserId(document.getUserId().getId());
            map.setFirstName(document.getUserId().getFirstName());
            List<String> appendantDocPathList = new ArrayList<>();
            List<String> appendantDocDownloadURLList = new ArrayList<>();
            for (int i = 0; i < document.getAppendantDocsList().size(); i++) {
                String appendantDocPath1 = document.getAppendantDocsList().get(i).getAppendantDocPath();
                appendantDocPathList.add(appendantDocPath1);
                String appendantDocDownloadUrl = document.getAppendantDocsList().get(i).getAppendantDocDownloadUrl();
                appendantDocDownloadURLList.add(appendantDocDownloadUrl);
            }
            map.setAppendantDocPath(appendantDocPathList);
            map.setAppendantDocDownloadUrl(appendantDocDownloadURLList);
            if (document.getReference() != null) {
                map.setImpReference(document.getReference().getRefName());
            }
            return map;
        })
                .collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    public Page<DocumentResponseDTO> getByDocType(DocumentType docType, Pageable pageable) {
        Long currentUserId = currentUserInfo.getCurrentUserId();
        List<Long> currentUserDepartmentIds = currentUserInfo.getCurrentUserDepartmentIds();
        Page<Document> documentList = null;
        if (docType != null) {
            documentList = documentRepo.findByDocumentTypeWithPagination(currentUserDepartmentIds,
                    docType, pageable);
        } else {
            return null;
        }

        List<DocumentResponseDTO> documentResponseDTO = documentList.stream().map(
                document -> {
                    DocumentResponseDTO map = mapper.map(document, DocumentResponseDTO.class);
                    map.setDocPath(document.getDocPath());
                    map.setDownloadUrl(document.getDownloadUrl());
                    List<String> appendantDocPath = new ArrayList<>();
                    List<String> appendantDocURL = new ArrayList<>();
                    for (int i = 0; i < document.getAppendantDocsList().size(); i++) {
                        String appendantDocPath1 = document.getAppendantDocsList().get(i).getAppendantDocPath();
                        appendantDocPath.add(appendantDocPath1);
                        String appendantDocDownloadUrl = document.getAppendantDocsList().get(i)
                                .getAppendantDocDownloadUrl();
                        appendantDocURL.add(appendantDocDownloadUrl);
                    }
                    map.setAppendantDocPath(appendantDocPath);
                    map.setAppendantDocDownloadUrl(appendantDocURL);
                    if (document.getReference() != null) {
                        map.setImpReference(document.getReference().getRefName());
                    }
                    return map;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(documentResponseDTO, pageable, documentList.getTotalElements());
    }

    // New method for partial update the document
    public Document updateDocumentPartially(Long id, DocumentDTO updatedDocumentDTO) throws IOException {
        Document document = documentRepo.findById(id).orElseThrow();
        Document previousDocument = new Document(document);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> details = new HashMap<>();
        if (document != null) {
            if (updatedDocumentDTO.getDeadline() != null) {
                map.put("deadline", "از " + document.getDeadline() + " به " + updatedDocumentDTO.getDeadline());
                document.setDeadline(updatedDocumentDTO.getDeadline());
                if (!Objects.equals(previousDocument.getDeadline(), document.getDeadline())) {
                    details.put("previous deadline " + previousDocument.getDeadline(),
                            "updated deadline" + document.getDeadline());
                }
            }
            if (updatedDocumentDTO.getCreationDate() != null) {
                document.setCreationDate(updatedDocumentDTO.getCreationDate());
                if (!Objects.equals(previousDocument.getCreationDate(), document.getCreationDate())) {
                    details.put("previous creationDate " + previousDocument.getCreationDate(),
                            "updated creationDate" + document.getCreationDate());
                }
            }
            if (updatedDocumentDTO.getInitialDate() != null) {
                map.put("initialDate",
                        "از " + document.getInitialDate() + " به " + updatedDocumentDTO.getInitialDate());
                document.setInitialDate(updatedDocumentDTO.getInitialDate());
                if (!Objects.equals(previousDocument.getInitialDate(), document.getInitialDate())) {
                    details.put("prevoius initialDate " + previousDocument.getInitialDate(),
                            "updated initialDate" + document.getInitialDate());
                }
            }
            if (updatedDocumentDTO.getSecondDate() != null) {
                map.put("secondDate", "از " + document.getSecondDate() + " به " + updatedDocumentDTO.getSecondDate());
                document.setSecondDate(updatedDocumentDTO.getSecondDate());
                if (!Objects.equals(previousDocument.getSecondDate(), document.getSecondDate())) {
                    details.put("previous secondDate " + previousDocument.getSecondDate(),
                            "updated secondDate " + document.getSecondDate());
                }
            }
            if (updatedDocumentDTO.getLinkingDocs() != null) {
                linkingDocService.deletelinks(id);
                for (Long linkId : updatedDocumentDTO.getLinkingDocs()) {
                    linkingDocService.linkDocuments(id, linkId);
                }
            }
            if (updatedDocumentDTO.getReceivedDate() != null) {
                document.setReceivedDate(updatedDocumentDTO.getReceivedDate());
            }
            if (updatedDocumentDTO.getSubject() != null) {
                map.put("subject", "از " + document.getSubject() + " به " + updatedDocumentDTO.getSubject());
                document.setSubject(updatedDocumentDTO.getSubject());
                if (!Objects.equals(previousDocument.getSubject(), document.getSubject())) {
                    details.put("previous subject " + previousDocument.getSubject(),
                            "updated subject " + document.getSubject());
                }
            }
            if (updatedDocumentDTO.getDocNumber() != null) {
                map.put("docNumber", "از " + document.getDocNumber() + " به " + updatedDocumentDTO.getDocNumber());
                document.setDocNumber(updatedDocumentDTO.getDocNumber());
                if (!Objects.equals(previousDocument.getDocNumber(), document.getDocNumber())) {
                    details.put("previous docNumber " + previousDocument.getDocNumber(),
                            "updated docNumber " + document.getDocNumber());
                }
            }

            if (updatedDocumentDTO.getDescription() != null) {
                document.setDescription(updatedDocumentDTO.getDescription());
            }

            if (updatedDocumentDTO.getDocNumber2() != null) {
                if (document.getDocNumber2() != null)
                    map.put("docNumber2",
                            "از " + document.getDocNumber2() + " به " + updatedDocumentDTO.getDocNumber2());
                if (document.getDocNumber2() == null)
                    map.put("docNumber2", "از " + updatedDocumentDTO.getDocNumber2());

                document.setDocNumber2(updatedDocumentDTO.getDocNumber2());
                if (!Objects.equals(previousDocument.getDocNumber2(), document.getDocNumber2())) {
                    details.put("previous docNumber2 " + previousDocument.getDocNumber2(),
                            "updated docNumber2 " + document.getDocNumber2());
                }
            }
            if (updatedDocumentDTO.getReferenceType() != null) {
                map.put("reference",
                        "از " + document.getReferenceType() + " به " + updatedDocumentDTO.getReferenceType());
                document.setReferenceType(updatedDocumentDTO.getReferenceType());
                if (!Objects.equals(previousDocument.getReferenceType(), document.getReferenceType())) {
                    details.put("previous ReferenceType " + previousDocument.getReferenceType(),
                            "updated ReferenceType " + document.getReferenceType());
                }
            }
            // if (updatedDocumentDTO.getReference() != null) {
            // DocReference docRef =
            // docRefRepository.findById(updatedDocumentDTO.getReference())
            // .orElseThrow(() -> new MyNotFoundException("The Referenc Not Found!"));
            // document.setReference(docRef);
            // }
            if (updatedDocumentDTO.getDocType() != null) {
                map.put("docType", "از " + document.getDocType() + " به " + updatedDocumentDTO.getDocType());
                document.setDocType(updatedDocumentDTO.getDocType());
                if (!Objects.equals(previousDocument.getDocType(), document.getDocType())) {
                    details.put("previous docType " + previousDocument.getDocType(),
                            "updated docType " + document.getDocType());
                }
            }
            if (updatedDocumentDTO.getExecutionType() != null) {
                map.put("executionType",
                        "از " + document.getExecutionType() + " به " + updatedDocumentDTO.getExecutionType());
                document.setExecutionType(updatedDocumentDTO.getExecutionType());
                if (!Objects.equals(previousDocument.getExecutionType(), document.getExecutionType())) {
                    details.put("previous executionType " + previousDocument.getExecutionType(),
                            "updated executionType " + document.getExecutionType());
                }
            }
            if (updatedDocumentDTO.getReference() != null) {
                DocReference docReference = docRefRepository.findById(updatedDocumentDTO.getReference())
                        .orElseThrow(() -> new MyNotFoundException(
                                "Document Reference with id " + updatedDocumentDTO.getReference() + " not found"));

                document.setReference(docReference);

                // Check if previous document reference is null
                if (previousDocument.getReference() != null) {
                    // Check if the references are different
                    if (!Objects.equals(previousDocument.getReference(), document.getReference())) {
                        // Both previous and updated references are not null and are different
                        details.put("previous document reference " + previousDocument.getReference().getRefName(),
                                "updated document reference " + document.getReference().getRefName());
                    }
                }
//                    } else {
//                        // Both previous and updated references are the same
//                        details.put("previous document reference " + previousDocument.getReference().getRefName(),
//                                "updated document reference " + document.getReference().getRefName());
//                    }
//                } else {
//                    // Previous reference is null, new reference is not null
//                    details.put("previous document reference " + null,
//                            "updated document reference " + document.getReference().getRefName());
//                }
//            } else {
//                // If the updated document reference is null, handle accordingly
//                document.setReference(null);
//
//                // Check if the previous reference was not null
//                if (previousDocument.getReference() != null) {
//                    details.put("previous document reference " + previousDocument.getReference().getRefName(),
//                            "updated document reference " + null);
//                } else {
//                    // Both previous and updated references are null
//                    details.put("previous document reference " + null,
//                            "updated document reference " + null);
//                }
            }

            if (updatedDocumentDTO.getMainDocument() != null) {
                MultipartFile mainDocument = updatedDocumentDTO.getMainDocument();
                String uniqueFileName = generateUniqueFileName(mainDocument.getOriginalFilename(), UPLOAD_DIRECTORY);
                String filePath = UPLOAD_DIRECTORY + uniqueFileName;
                Path path = get(filePath);
                Files.write(path, mainDocument.getBytes());
                document.setDocName(mainDocument.getOriginalFilename());
                document.setDocPath(pathToDownloadUrlService.getAccessUrl(uniqueFileName));
                document.setDownloadUrl(pathToDownloadUrlService.getDownloadUrl(uniqueFileName));
            }

            document.setUpdateDate(LocalDate.now());
            Document update = documentRepo.save(document);
            for (SendDoc sendDoc : document.getSendDocList()) {
                for (Users user : sendDoc.getReceiverDepartment().getActiveUsers()) {
                    map.put("sendDoc_id", sendDoc.getSendDocId() + "");
                    Notification notification = new Notification(user, document, map);
                    notificationRepository.save(notification);
                }
            }

            List<Department> department = update.getUserId().getDepartment();
            ActivityLog activityLog = new ActivityLog();
            String content = activityLog.MapToString(details);
            activityLogService.logsActivity("Document", document.getDocId(), "Updated", department, content);
        }
        return document;
    }

    // method for generating unique file Name
    public String generateUniqueFileName(String originalFileName, String directory) {
        String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));

        // Generate a unique file name by appending a timestamp
        // String uniqueFileName = fileNameWithoutExtension +"_"+
        // System.currentTimeMillis() + fileExtension;
        // Generate a unique file name with UUID and timestamp
        String uniqueFileName = fileNameWithoutExtension + "_" + UUID.randomUUID().toString() + "_"
                + System.currentTimeMillis() + fileExtension;
        Path filePath = Paths.get(directory, uniqueFileName);

        // Check if the file already exists, and if so, generate a new name recursively
        if (Files.exists(filePath)) {
            return generateUniqueFileName(originalFileName, directory);
        }
        return uniqueFileName;
    }

    public Long countDocumentOfCurrentUserDepartment() {
        Long currentUserId = currentUserInfo.getCurrentUserId();
        List<Long> currentUserDepartments = currentUserInfo.getCurrentUserDepartmentIds();
        Long count = documentRepo.CountNumberOfDocumentsOfCurrentUserDepartment(currentUserId, currentUserDepartments);
        return count;
    }

    public Long countDocumentOfCurrentUser() {
        Long currentUserId = currentUserInfo.getCurrentUserId();
        Long count = documentRepo.CountNumberOfDocumentsOfCurrentUser(currentUserId);
        return count;
    }

    public Long CountDocumentOfCurrentUserDepartmentByDocType(DocumentType docType) {
        Long currentUserId = currentUserInfo.getCurrentUserId();
        List<Department> currentUserDepartments = currentUserInfo.getCurrentUserDepartments();

        Long count = documentRepo.countDocumentOfCurrentUserDepartmentByDocType(currentUserDepartments,
                docType);
        return count;
    }

    public void addNewAppendantDocument(Long docId, MultipartFile file) throws IOException {
        Document document = getSingleDocument(docId)
                .orElseThrow(() -> new MyNotFoundException("document with id " + docId + " not found"));
        if (file != null && !file.isEmpty() && file.getSize() > 0) {
            System.out.println(file.getOriginalFilename());
            List<AppendantDocs> appendantDocsList = document.getAppendantDocsList();
            System.out.println(appendantDocsList.size());
            AppendantDocs appendantDocs = new AppendantDocs();
            String uniqueFileName = generateUniqueFileName(file.getOriginalFilename(), UPLOAD_DIRECTORY);
            // //Now upload file
            String filePath = UPLOAD_DIRECTORY + uniqueFileName;
            Path path = Paths.get(filePath);
            Files.write(path, file.getBytes());
            appendantDocs.setAppendantDocName(file.getOriginalFilename());
            log.error("the new applendent unique name: " + uniqueFileName);
            appendantDocs.setAppendantDocPath(pathToDownloadUrlService.getAccessUrl(uniqueFileName));
            appendantDocs.setAppendantDocDownloadUrl(pathToDownloadUrlService.getDownloadUrl(uniqueFileName));
            AppendantDocs save1 = appendantDocRepo.save(appendantDocs);
            appendantDocsList.add(appendantDocs);
            document.setAppendantDocsList(appendantDocsList);
            Document save = documentRepo.save(document);
            List<Department> department = save.getUserId().getDepartment();
            Map<String, Object> details = new HashMap<>();
            details.put("added appendantDoc title ", save1.getAppendantDocName());
            ActivityLog activityLog = new ActivityLog();
            String content = activityLog.MapToString(details);
            activityLogService.logsActivity("AppendantDocument", appendantDocs.getAppendantDocId(),
                    "new appendant document added", department, content);
        } else {
            throw new MyNotFoundException("file not found!");
        }
    }

    public void removeAppendantDoc(Long docId, Long appendantDocId) {
        if (appendantDocId != null) {
            AppendantDocs appendantDocs = appendantDocRepo.findById(appendantDocId).orElseThrow();
            if (fileUploadService.deleteFiles(appendantDocs.getAppendantDocPath())) {
                log.info("appendent file removed!");
                appendantDocRepo.delete(appendantDocs);
            }
            Document save = documentRepo.findById(docId).orElse(null);
            List<Department> department = save.getUserId().getDepartment();
            Map<String, Object> details = new HashMap<>();
            details.put("removed appendantDoc Name", appendantDocs.getAppendantDocName());
            ActivityLog activityLog = new ActivityLog();
            String content = activityLog.MapToString(details);
            activityLogService.logsActivity("AppendantDocument", appendantDocId,
                    "remove appendant document from list", department, content);
        }
    }

    public Page<DocumentResponseDTO> getDocumentsOfDepartment(
            String docNumber,
            String subject,
            DocumentType docType,
            Long name,
            Pageable pageable) {
        List<Long> currentUserDepartmentIds = currentUserInfo.getCurrentUserDepartmentIds();
        Page<Document> documentsOfDepartment = documentRepo.findDocumentsOfDepartment(
                currentUserDepartmentIds, docNumber, docType, subject,name, pageable);
        List<DocumentResponseDTO> collect = documentsOfDepartment.stream().map(document -> {
            DocumentResponseDTO documentResponseDTO = mapEntityToDTO(document);
            return documentResponseDTO;
        }).collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, documentsOfDepartment.getTotalElements());
    }
    public Page<DocumentResponseDTO> getFilteredDocuments(DocumentCriteria documentCriteria
            , Pageable sortedPageable) {
        Page<Document> filteredDocuments = documentRepo.findFilteredDocuments(documentCriteria, sortedPageable);
        List<DocumentResponseDTO> documentResponseDTOList = filteredDocuments.stream().map(document -> {
            DocumentResponseDTO documentResponseDTO = mapEntityToDTO(document);
            return documentResponseDTO;
        }).collect(Collectors.toList());
        return new PageImpl<>(documentResponseDTOList,sortedPageable,filteredDocuments.getTotalElements());
    }
    
    public List<DocForLinkingDTO> getforlinking(
            String searchterm) {
        List<Long> currentUserDepartmentIds = currentUserInfo.getCurrentUserDepartmentIds();
        List<Document> documentsOfDepartment = documentRepo.findbycriteria(
                currentUserDepartmentIds, searchterm);
        List<DocForLinkingDTO> collect = documentsOfDepartment.stream().map(document -> {
            DocForLinkingDTO documentResponseDTO = mapEntityToLinkingDTO(document);
            return documentResponseDTO;
        }).collect(Collectors.toList());

        return collect;
    }

    private DocForLinkingDTO mapEntityToLinkingDTO(Document document) {
        DocForLinkingDTO map = mapper.map(document, DocForLinkingDTO.class);
        return map;
    }

    private DocumentResponseDTO mapEntityToDTO(Document document) {
        DocumentResponseDTO map = mapper.map(document, DocumentResponseDTO.class);
        if (document.getReference() != null) {
            map.setImpReference(document.getReference().getRefName());
        }
        List<String> appendantDocPathList = new ArrayList<>();
        List<String> appendantDocDownloadURLList = new ArrayList<>();
        for (int i = 0; i < document.getAppendantDocsList().size(); i++) {
            String appendantDocPath = document.getAppendantDocsList().get(i).getAppendantDocPath();
            appendantDocPathList.add(appendantDocPath);
            String appendantDocDownloadUrl = document.getAppendantDocsList().get(i).getAppendantDocDownloadUrl();
            appendantDocDownloadURLList.add(appendantDocDownloadUrl);
        }
        map.setAppendantDocPath(appendantDocPathList);
        map.setAppendantDocDownloadUrl(appendantDocDownloadURLList);
        map.setUserId(document.getUserId().getId());
        map.setFirstName(document.getUserId().getFirstName());
        map.setDepName(document.getUserId().getDepartment().get(0).getDepName());
        return map;
    }

    @Transactional
    public void deleteDocument(Long docId) {
        Document document = documentRepo.findById(docId).orElseThrow();
        if (document != null) {
            List<SendDoc> children = sendDocumentsRepository.findByDocumentId(document);
            for (SendDoc child : children) {
                sendDocumentService.deleteSendDocument(child.getSendDocId());
            }
            List<AppendantDocs> appendantDocs = document.getAppendantDocsList();
            for (AppendantDocs appendent : appendantDocs) {
                if (fileUploadService.deleteFiles(appendent.getAppendantDocPath())) {
                    appendantDocRepo.delete(appendent);
                }
            }
            documentRepo.delete(document);
            Map<String, Object> details = new HashMap<>();
            details.put("deleted document subject", document.getSubject());
            details.put("deleted document Number", document.getDocNumber());
            details.put("deleted document Type", document.getDocType());
            ActivityLog activityLog = new ActivityLog();
            String content = activityLog.MapToString(details);
            activityLogService.logsActivity("Document", document.getDocId(), "Deleted",
                    document.getUserId().getDepartment(), content);
        }
    }


}
