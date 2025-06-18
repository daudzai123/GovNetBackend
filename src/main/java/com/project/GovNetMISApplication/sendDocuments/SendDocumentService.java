package com.project.GovNetMISApplication.sendDocuments;

import com.project.GovNetMISApplication.ActivitiesLog.ActivityLog;
import com.project.GovNetMISApplication.ActivitiesLog.ActivityLogService;
import com.project.GovNetMISApplication.Comments.Comment;
import com.project.GovNetMISApplication.Comments.CommentRepository;
import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.Departments.DepartmentRepository;
import com.project.GovNetMISApplication.Departments.DepartmentService;
import com.project.GovNetMISApplication.DocumentReport.DocReport;
import com.project.GovNetMISApplication.DocumentReport.DocReportRepository;
import com.project.GovNetMISApplication.Documents.Document;
import com.project.GovNetMISApplication.Documents.DocumentRepository;
import com.project.GovNetMISApplication.Documents.DocumentService;
import com.project.GovNetMISApplication.Documents.Enums.DocumentType;
import com.project.GovNetMISApplication.Exceptions.MyNotFoundException;
import com.project.GovNetMISApplication.Notifications.Notification;
import com.project.GovNetMISApplication.Notifications.NotificationRepository;
import com.project.GovNetMISApplication.sendDocuments.Enums.Secretness;
import com.project.GovNetMISApplication.sendDocuments.Enums.documentStatus;
import com.project.GovNetMISApplication.sendDocuments.Enums.sendStatus;
import com.project.GovNetMISApplication.services.FileUploadService;
import com.project.GovNetMISApplication.user.CurrentUserInfoService;
import com.project.GovNetMISApplication.user.Users;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SendDocumentService {
    @Autowired
    private SendDocumentsRepository sendDocumentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DocumentService documentSer;
    @Autowired
    private DepartmentService departmentSer;
    @Autowired
    private CurrentUserInfoService currentUserInfoSer;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private ActivityLogService activityLogService;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private DocReportRepository docReportRepository;
    @Autowired
    private FileUploadService fileUploadService;

    // new code for adding records in SendDoc
    public void addSendDocuments(SendDocumentRequestDTO sendDocument) {

        Document document = documentSer.getSingleDocument(sendDocument.getDocumentId()).orElseThrow();
        if (document != null) {
            Department department = new Department();
            Department sender = null;
            SendDoc parentDoc = null;
//            if (sendDocument.getParentSendDoc() != null) {
//                parentDoc = sendDocumentRepo.findById(sendDocument.getParentSendDoc())
//                        .orElseThrow(() -> new MyNotFoundException("The parent sendDoc not found!"));
//                sender = parentDoc.getSenderDepartment();
//            } else {
                sender = currentUserInfoSer.getCurrentUserDepartments().get(0);
//            }
            for (Long receiverDepartment : sendDocument.getReceiverDepartment()) {
                if (checkduplicate(document.getDocId(), receiverDepartment, sender.getDepId())) {
                    continue;
                }
                SendDoc sendDoc = new SendDoc();
                sendDoc.setDocStatus(documentStatus.IN_PROGRESS);
                sendDoc.setGuide(sendDocument.getGuide());
                sendDoc.setSecret(sendDocument.getSecret());
                sendDoc.setSendingDate(LocalDateTime.now());
                sendDoc.setDocumentId(document);
                sendDoc.setSenderUserid(currentUserInfoSer.getCurrentUser());
                sendDoc.setSendOrginalDoc(sendDocument.isSendOriginalDocument());
                sendDoc.setSendAppendentDocs(sendDocument.isSendAppendentDocuments());
                department = departmentSer.getDepartment(receiverDepartment);
                sendDoc.setReceiverDepartment(department);

                if (parentDoc != null) {
                    sendDoc.setParentSendDoc(parentDoc);
                    if (parentDoc.getAncestor() != null) {
                        sendDoc.setAncestor(parentDoc.getSendDocId() + "," + parentDoc.getAncestor());
                    } else {
                        sendDoc.setAncestor(parentDoc.getSendDocId() + "");
                    }
                }
                sendDoc.setSenderDepartment(sender);
                SendDoc created = sendDocumentRepo.save(sendDoc);
                Department senderDepartment = created.getSenderDepartment();
                Department receiverDepartment1 = created.getReceiverDepartment();
                List<Department> departmentList = new ArrayList<>();
                departmentList.add(senderDepartment);
                departmentList.add(receiverDepartment1);
                Map<String,Object> details=new HashMap<>();
                details.put("sent document subject ",created.getDocumentId().getSubject());
                details.put("sent document Number ",created.getDocumentId().getDocNumber());
                details.put("sender Department ",created.getSenderDepartment().getDepName());
                details.put("receiver Department ",created.getReceiverDepartment().getDepName());
                ActivityLog activityLog=new ActivityLog();
                String content = activityLog.MapToString(details);
                activityLogService.logsActivity("Send Document", created.getSendDocId(), "sent Document",
                        departmentList,content);
                for (Users user : department.getActiveUsers()) {
                    if (sendDocument.getSecret().equals(Secretness.SECRET)) {
                        boolean hasSecretViewAuthority = user.getAuthorities().stream()
                                .anyMatch(authority -> authority.getAuthority().equals("ROLE_secret_view"));

                        if (hasSecretViewAuthority) {
                            Notification notification = new Notification(created, user);
                            notificationRepository.save(notification);
                        }
                    } else {
                        Notification notification = new Notification(created, user);
                        notificationRepository.save(notification);
                    }
                }
            }
        } else {
            throw new MyNotFoundException("the Document not found!");
        }
    }

    private boolean checkduplicate(Long docId, Long receiverDepartment, Long senderDepartmentId) {
        System.out.println(
                "the output of the check " + senderDepartmentId + "-----------------------------------------------");
        return sendDocumentRepo.existsByDocumentIdAndReceiverDepartmentId(docId, receiverDepartment,
                senderDepartmentId);
    }

    // new method
    public List<SendDocResponseDTO> getAllSentDocsByDepartment() {
        Long currentUser = currentUserInfoSer.getCurrentUserId();
        List<Long> currentUserDepartmentIds = currentUserInfoSer.getCurrentUserDepartmentIds();
        List<SendDoc> sentDocumentByDepartments = new ArrayList<>();
        if (currentUser != null) {
            sentDocumentByDepartments = sendDocumentRepo.findSentDocumentByDepartments(
                    currentUserDepartmentIds);
        }
        // List<SendDoc> allSentDocs = sendDocumentRepo.findAll();

        List<SendDocResponseDTO> sendDocResponseDTOS = mapEntityToDTO(sentDocumentByDepartments);
        return sendDocResponseDTOS;
    }

    public List<SendDocResponseDTO> getDocumentsReceivedByDepartments() {

        List<Long> currentUserDepartmentIds = currentUserInfoSer.getCurrentUserDepartmentIds();
        List<SendDoc> receivedDocumentByDepartments = sendDocumentRepo
                .findReceivedDocumentByDepartments(currentUserDepartmentIds);
        List<SendDocResponseDTO> receivedDocumentsByDepartments = mapEntityToDTO(receivedDocumentByDepartments);
        return receivedDocumentsByDepartments;
    }

    public List<SendDocResponseDTO> getDocumentsByType(DocumentType docType) {
        Long currentUserId = currentUserInfoSer.getCurrentUserId();
        List<Long> currentUserDepartmentIds = currentUserInfoSer.getCurrentUserDepartmentIds();
        List<SendDoc> bydocumentIdDocType = sendDocumentRepo.findSentDocumentByDocumentType(currentUserId,
                currentUserDepartmentIds, docType);
        List<SendDocResponseDTO> sendDocResponseDTOS = mapEntityToDTO(bydocumentIdDocType);
        return sendDocResponseDTOS;
    }

    public List<SendDocResponseDTO> getReceivedDocsByDocType(DocumentType docType) {
        List<Long> currentUserDepartmentIds = currentUserInfoSer.getCurrentUserDepartmentIds();
        List<SendDoc> receivedDocumentByDocumentType = sendDocumentRepo
                .findReceivedDocumentByDocumentType(currentUserDepartmentIds, docType);
        List<SendDocResponseDTO> sendDocResponseDTOS = mapEntityToDTO(receivedDocumentByDocumentType);
        return sendDocResponseDTOS;
    }

    public List<SendDocResponseDTO> getSentDocsByStatus(documentStatus docStatus) {
        Long currentUserId = currentUserInfoSer.getCurrentUserId();
        List<Long> currentUserDepartmentIds = currentUserInfoSer.getCurrentUserDepartmentIds();
        List<SendDoc> sentDocumentByDocumentStatus = sendDocumentRepo.findSentDocumentByDocumentStatus(currentUserId,
                currentUserDepartmentIds, docStatus);
        List<SendDocResponseDTO> sendDocResponseDTOS = mapEntityToDTO(sentDocumentByDocumentStatus);
        return sendDocResponseDTOS;
    }

    public List<SendDocResponseDTO> getReceivedDocsByStatus(documentStatus docStatus) {
        List<Long> currentUserDepartmentIds = currentUserInfoSer.getCurrentUserDepartmentIds();
        List<SendDoc> receivedDocumentByDocumentStatus = sendDocumentRepo
                .findReceivedDocumentByDocumentStatus(currentUserDepartmentIds, docStatus);
        List<SendDocResponseDTO> sendDocResponseDTOS = mapEntityToDTO(receivedDocumentByDocumentStatus);
        return sendDocResponseDTOS;
    }

    public List<SendDocResponseDTO> getReceivedDocsBySendingStatus(sendStatus sendingStatus) {
        List<Long> currentUserDepartmentIds = currentUserInfoSer.getCurrentUserDepartmentIds();
        List<SendDoc> receivedDocumentByDocumentStatus = sendDocumentRepo
                .findReceivedDocumentBySendingStatus(currentUserDepartmentIds, sendingStatus);
        List<SendDocResponseDTO> sendDocResponseDTOS = mapEntityToDTO(receivedDocumentByDocumentStatus);
        return sendDocResponseDTOS;
    }

    public void updateDocumentStatus(Long sendDocId, documentStatus request) {
        SendDoc byId = sendDocumentRepo.findById(sendDocId)
                .orElseThrow(() -> new EntityNotFoundException("SendDocument with id " + sendDocId + " not found"));

        // update the document status
        byId.setDocStatus(request);
        sendDocumentRepo.save(byId);
    }

    public SendDoc getSendDocumentById(Long sendDocId) {
        SendDoc byId = sendDocumentRepo.findById(sendDocId)
                .orElseThrow(() -> new MyNotFoundException("send document not found with id " + sendDocId));
        if (!byId.isSendOrginalDoc()) {
            byId.getDocumentId().setDownloadUrl(null);
            byId.getDocumentId().setAppendantDocsList(null);
        }

        return byId;
    }

    public SendDoc getSendDocById(Long sendDocId) {
        // return sendDocumentRepo.findBySendDocId(sendDocId);
        Long currentUserId = currentUserInfoSer.getCurrentUserId();
        List<Long> currentUserDepartmentIds = currentUserInfoSer.getCurrentUserDepartmentIds();
        return sendDocumentRepo.findSentDocumentByIdByDepartments(currentUserId, currentUserDepartmentIds, sendDocId);
    }

    public SendDoc getReceivedDocBySendDocId(Long sendDocId) {
        List<Long> currentUserDepartmentIds = currentUserInfoSer.getCurrentUserDepartmentIds();
        SendDoc receivedDocumentBySendDocId = sendDocumentRepo.findReceivedDocumentBySendDocId(currentUserDepartmentIds,
                sendDocId);
        if (receivedDocumentBySendDocId == null) {
            throw new EntityNotFoundException("No record found with sendDocId: " + sendDocId);
        }
        receivedDocumentBySendDocId.markAsSeen();
        sendDocumentRepo.save(receivedDocumentBySendDocId);
        
        if (!receivedDocumentBySendDocId.isSendOrginalDoc()) {
            receivedDocumentBySendDocId.getDocumentId().setDownloadUrl(null);
        }
        if (!receivedDocumentBySendDocId.isSendAppendentDocs()) {
            receivedDocumentBySendDocId.getDocumentId().setAppendantDocsList(null);
        }
        return receivedDocumentBySendDocId;
    }

    public List<SendDocResponseDTO> getAllSentDocByDocId(Long docId) {
        Long currentUserId = currentUserInfoSer.getCurrentUserId();
        List<Long> currentUserDepartmentIds = currentUserInfoSer.getCurrentUserDepartmentIds();
        List<SendDoc> sentDocumentByDocumentIdByDepartments = sendDocumentRepo
                .findSentDocumentByDocumentIdByDepartments(currentUserId, currentUserDepartmentIds, docId);
        List<SendDocResponseDTO> sendDocResponseDTOS = mapEntityToDTO(sentDocumentByDocumentIdByDepartments);
        return sendDocResponseDTOS;
    }

    public List<SendDocResponseDTO> getAllSentDocByDocId1(Long docId) {
        List<SendDoc> sentDocumentByDocumentIdByDepartments = sendDocumentRepo
                .findSentDocumentByDocumentIdByDepartments(docId);
        List<SendDocResponseDTO> sendDocResponseDTOS = mapEntityToDTO(sentDocumentByDocumentIdByDepartments);
        return sendDocResponseDTOS;
    }

    public List<SendDoc> getReceivedDocsByDocId(Long docId) {
        return null;
    }

    private List<SendDocResponseDTO> mapEntityToDTO(List<SendDoc> sentDocumentByDepartments) {
        List<SendDocResponseDTO> sendDocResponseDTOS = sentDocumentByDepartments.stream()
                .map(doc -> {
                    SendDocResponseDTO map = modelMapper.map(doc, SendDocResponseDTO.class);
                    if (doc.getSenderDepartment() != null)
                        map.setSenderDepartment(new senderDepartmentDTO(doc.getSenderDepartment().getDepId(),
                                doc.getSenderDepartment().getDepName()));
                    if (doc.getReceiverDepartment() != null)
                        map.setReceiverDepartments(new receiverDepartmentDTO(doc.getReceiverDepartment().getDepId(),
                                doc.getReceiverDepartment().getDepName()));
                    if (doc.getParentSendDoc() != null) {
                        map.setParent_SendDoc_Id(doc.getParentSendDoc().getSendDocId());
                    }
                    if (doc.getDocumentId().getReference() != null){
                        map.setImpReference(doc.getDocumentId().getReference().getRefName());
                    }


                    if (doc.getDocumentId() != null) {
                        Optional<Document> singleDocument = documentSer
                                .getSingleDocument(doc.getDocumentId().getDocId());
                        singleDocument.ifPresent(u -> {
                            map.setDocNumber(u.getDocNumber());
                            map.setDeadline(u.getDeadline());
                            map.setCreationDate(u.getCreationDate());
                            if (doc.isSendOrginalDoc()) {
                                map.setDownloadUrl(u.getDownloadUrl());
                            } else {
                                map.setDownloadUrl(null);
                            }
                            map.setSubject(u.getSubject());
                            map.setUpdateDate(u.getUpdateDate());
                            map.setDocId(u.getDocId());
                            map.setDocType(u.getDocType());
                        });
                    }
                    return map;
                })
                .collect(Collectors.toList());
        return sendDocResponseDTOS;
    }

    private List<SenDocReportDTO> mapEntityToDTOForReport(List<SendDoc> sentDocumentByDepartments) {
        List<SenDocReportDTO> sendDocResponseDTOS = sentDocumentByDepartments.stream()
                .map(doc -> {
                    SenDocReportDTO map = modelMapper.map(doc, SenDocReportDTO.class);
                    if (doc.getSenderDepartment() != null)
                        map.setSenderDepartment(new senderDepartmentDTO(doc.getSenderDepartment().getDepId(),
                                doc.getSenderDepartment().getDepName()));
                    if (doc.getReceiverDepartment() != null)
                        map.setReceiverDepartments(new receiverDepartmentDTO(doc.getReceiverDepartment().getDepId(),
                                doc.getReceiverDepartment().getDepName()));
                    if (doc.getDocumentId() != null) {
                        Optional<Document> singleDocument = documentSer
                                .getSingleDocument(doc.getDocumentId().getDocId());
                        singleDocument.ifPresent(u -> {
                            map.setDocNumber(u.getDocNumber());
                            map.setDeadline(u.getDeadline());
                            map.setSubject(u.getSubject());
                            map.setDocId(u.getDocId());
                            map.setDocType(u.getDocType());
                        });
                    }

                    return map;
                })
                .collect(Collectors.toList());
        return sendDocResponseDTOS;
    }

    public Page<SendDocResponseDTO> findByCriteria(sendDocCriteria criteria, Pageable pageable) {

        // if (pageable.getPageNumber()!=0) {
        // pageable = PageRequest.of(pageable.getPageNumber() - 1,
        // pageable.getPageSize(), pageable.getSort());
        // }
        Specification<SendDoc> spec = sendDocumentRepo.buildSpecification(criteria);
        Page<SendDoc> page = sendDocumentRepo.findAll(spec, pageable);
        List<SendDocResponseDTO> sendDocResponseDTOS = mapEntityToDTO(page.getContent());
        // Pageable pageable2 = PageRequest.of(pageable.getPageNumber() + 1,
        // pageable.getPageSize(), pageable.getSort());
        return new PageImpl<>(sendDocResponseDTOS, pageable, page.getTotalElements());
    }

    public List<SenDocReportDTO> findByCriteria(sendDocCriteria criteria) {
        Specification<SendDoc> spec = sendDocumentRepo.buildSpecification(criteria);
        List<SendDoc> all = sendDocumentRepo.findAll(spec);
        List<SenDocReportDTO> sendDocResponseDTOS1 = mapEntityToDTOForReport(all);
        return sendDocResponseDTOS1;
    }

    public List<SendDocResponseDTO> getsendDocTree(Long sendDocId) {

        List<Long> sendDocIds = sendDocumentRepo.findFamilyTree(sendDocId);
        sendDocIds.addAll(sendDocumentRepo.findAllWithChildren(sendDocId));
        sendDocIds.add(sendDocId);
        List<Long> distinctIds = sendDocIds.stream().distinct().collect(Collectors.toList());
        if (sendDocIds.isEmpty()) {
            // handle empty list case
            return Collections.emptyList();
        }
        return mapEntityToDTO(sendDocumentRepo.findAllById(distinctIds));

    }

    public Page<SendDocResponseDTO> getAllDeadlinedDocuments(Pageable pageable) {
        List<SendDocResponseDTO> allSentDocsByDepartment = this.getAllSentDocsByDepartment();
        List<documentStatus> collect = allSentDocsByDepartment.stream().map(allSentDocs -> {
            documentStatus docStatus = allSentDocs.getDocStatus();
            return docStatus;
        }).collect(Collectors.toList());
        LocalDate currentDate = LocalDate.now();
        List<Long> currentUserDepartmentIds = currentUserInfoSer.getCurrentUserDepartmentIds();
        Page<SendDoc> allSentDocumentsDeadlined = sendDocumentRepo.findAllSentDocumentsDeadlined(collect, currentDate,
                currentUserDepartmentIds,pageable);
        List<SendDocResponseDTO> sendDocResponseDTOS = mapEntityToDTO(allSentDocumentsDeadlined.getContent());
        return new PageImpl<>(sendDocResponseDTOS,pageable,allSentDocumentsDeadlined.getTotalElements());
    }

    public void updateSendDocument(Long sendDocId, SendDocumentRequestDTO sendDocumentRequestDTO) {
        SendDoc sendDoc = sendDocumentRepo.findById(sendDocId)
                .orElseThrow(() -> new MyNotFoundException("send Document with id " + sendDocId + " not found"));
        SendDoc previousSendDoc=new SendDoc(sendDoc);
        Map<String, Object> map1 = new HashMap<>();
        Map<String,Object> details=new HashMap<>();

        if (sendDocumentRequestDTO.getSecret() != null) {
            sendDoc.setSecret(sendDocumentRequestDTO.getSecret());
            if (!previousSendDoc.getSecret().equals(sendDoc.getSecret())){
                details.put("Previous sent doc secretness "+previousSendDoc.getSecret(),"updated sent doc secretness "+sendDoc.getSecret());
            }
        }
        if (sendDocumentRequestDTO.getGuide() != null) {
            map1.put("guide", sendDoc.getGuide()+" to " +sendDocumentRequestDTO.getGuide());
            sendDoc.setGuide(sendDocumentRequestDTO.getGuide());
            if (!previousSendDoc.getGuide().equals(sendDoc.getGuide())){
                details.put("Previous Guide "+previousSendDoc.getGuide(),"updated Guide "+sendDoc.getGuide());
            }
            
        }
        if (sendDoc.isSendOrginalDoc() != sendDocumentRequestDTO.isSendOriginalDocument()) {
            sendDoc.setSendOrginalDoc(sendDocumentRequestDTO.isSendOriginalDocument());
            if (previousSendDoc.isSendOrginalDoc() !=sendDoc.isSendOrginalDoc()){
                details.put("previous state of sent original documents"+previousSendDoc.isSendOrginalDoc(),"update state of sent original documents"+sendDoc.isSendOrginalDoc());
            }
        }
        if (sendDoc.isSendAppendentDocs() != sendDocumentRequestDTO.isSendAppendentDocuments()) {
            sendDoc.setSendAppendentDocs(sendDocumentRequestDTO.isSendAppendentDocuments());
            if (previousSendDoc.isSendAppendentDocs()!=sendDoc.isSendAppendentDocs()){
                details.put("previous state of sent appendant documents"+previousSendDoc.isSendAppendentDocs(),"update state of sent appendant documents"+sendDoc.isSendAppendentDocs());
            }
        }
        if (sendDocumentRequestDTO.getDocStatus() != null) {
            sendDoc.setDocStatus(sendDocumentRequestDTO.getDocStatus());
        }
        if (sendDocumentRequestDTO.getDocumentId() != null) {
            Document document = documentRepository.findById(sendDocumentRequestDTO.getDocumentId())
                    .orElseThrow(() -> new MyNotFoundException(
                            "Document with id " + sendDocumentRequestDTO.getDocumentId() + " not found"));
            sendDoc.setDocumentId(document);
        }
        List<Department> receiverDepartments = new ArrayList<>();
        if (sendDocumentRequestDTO.getReceiverDepartment() != null) {
            for (Long departmentId : sendDocumentRequestDTO.getReceiverDepartment()) {
                Department department = departmentRepository.findById(departmentId)
                        .orElseThrow(
                                () -> new MyNotFoundException("Department with id " + departmentId + " not found"));
                sendDoc.setReceiverDepartment(department);
                receiverDepartments.add(department);
            }
        }
        if (sendDocumentRequestDTO.getParentSendDoc() != null) {
            SendDoc parentSendDoc = sendDocumentRepo.findById(sendDocumentRequestDTO.getParentSendDoc())
                    .orElseThrow(() -> new MyNotFoundException("Parent Send Document with id "
                            + sendDocumentRequestDTO.getParentSendDoc() + " not found"));
            sendDoc.setParentSendDoc(parentSendDoc);
        }
        SendDoc save = sendDocumentRepo.save(sendDoc);
        Department senderDepartment = save.getSenderDepartment();
        receiverDepartments.add(senderDepartment);
        for (Users user : sendDoc.getReceiverDepartment().getUsers()) {
            Notification notification = new Notification(user, sendDoc, map1);
                        notificationRepository.save(notification);
        }
        ActivityLog activityLog=new ActivityLog();
        String content = activityLog.MapToString(details);
        activityLogService.logsActivity("send document", save.getSendDocId(), "update", receiverDepartments,content);
    }

    @Transactional
    public boolean deleteSendDocument(Long docId) {
        SendDoc document = sendDocumentRepo.findById(docId).orElseThrow();
        if (document != null) {
            List<Comment> children = commentRepository.findAllBySendDocId(docId);
            commentRepository.deleteAll(children);
            List<DocReport> docReports = docReportRepository.findBysendDocId(docId);
            for (DocReport child : docReports) {
                if (fileUploadService.deleteFiles(child.getDocPath())) {
                    docReportRepository.delete(child);
                }
            }
            sendDocumentRepo.delete(document);
            Department senderDepartment=document.getSenderDepartment();
            Department receiverDepartment = document.getReceiverDepartment();
            List<Department> departmentList=new ArrayList<>();
            departmentList.add(senderDepartment);
            departmentList.add(receiverDepartment);
            Map<String,Object> details=new HashMap<>();
            details.put("deleted sent document having",
                    " subject "+document.getDocumentId().getSubject()+
                            ", Number "+document.getDocumentId().getDocNumber()+
                            ", type "+document.getDocumentId().getDocType()+
                    ", sender department "+document.getSenderDepartment().getDepName()+
                            ", receiver department "+document.getReceiverDepartment().getDepName());
            ActivityLog activityLog=new ActivityLog();
            String content = activityLog.MapToString(details);
            activityLogService.logsActivity("send Doc ",document.getSendDocId(),"Deleted",departmentList,content);
            return true;
        }
        return false;
    }
}
