package com.project.GovNetMISApplication.DocumentReport;

import com.project.GovNetMISApplication.ActivitiesLog.ActivityLog;
import com.project.GovNetMISApplication.ActivitiesLog.ActivityLogService;
import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.Documents.DocumentService;
import com.project.GovNetMISApplication.Documents.PathToDownloadUrlService;
import com.project.GovNetMISApplication.Exceptions.MyNotFoundException;
import com.project.GovNetMISApplication.sendDocuments.SendDoc;
import com.project.GovNetMISApplication.sendDocuments.SendDocumentService;
import com.project.GovNetMISApplication.sendDocuments.SendDocumentsRepository;
import com.project.GovNetMISApplication.user.CurrentUserInfoService;
import com.project.GovNetMISApplication.user.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocReportService {
    @Value("${file.upload-dir}")
    private String UPLOAD_DIRECTORY;
    private final DocReportRepository docReportRepository;
    private final ModelMapper modelMapper;

    private final SendDocumentService sendDocumentService;
    @Autowired
    private SendDocumentsRepository sendDocumentsRepository;
    private final CurrentUserInfoService currentUserInfoService;
    private final DocumentService documentService;
    private final PathToDownloadUrlService pathToDownloadUrlService;
    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    public DocReportService(DocReportRepository docReportRepository, ModelMapper modelMapper,
            SendDocumentService sendDocumentService, CurrentUserInfoService currentUserInfoService,
            DocumentService documentService, PathToDownloadUrlService pathToDownloadUrlService) {
        this.docReportRepository = docReportRepository;
        this.modelMapper = modelMapper;
        this.sendDocumentService = sendDocumentService;
        this.currentUserInfoService = currentUserInfoService;
        this.documentService = documentService;
        this.pathToDownloadUrlService = pathToDownloadUrlService;
    }

    public void addDocReport(DocReportRequestDTO docReportDTO, MultipartFile file) throws IOException {
        SendDoc sendDoc = sendDocumentService.getSendDocumentById(docReportDTO.getSendDocId());
        // Department receiverDepartments = sendDoc.getReceiverDepartment();

        Users currentUser = currentUserInfoService.getCurrentUser();
        if (currentUser != null && currentUserInfoService.getCurrentUserDepartments() != null) {

            // boolean
            // hasCommonDepartment=receiverDepartments.equals(currentUserInfoService.getCurrentUserDepartments());
            // if (hasCommonDepartment)
            // {
            DocReport docReport = modelMapper.map(docReportDTO, DocReport.class);
            docReport.setSendDoc(sendDoc);
            docReport.setDate(LocalDateTime.now());
            docReport.setDocStatus(docReportDTO.getDocStatus());
            docReport.setUser(currentUserInfoService.getCurrentUser());
            if (file !=null && file.getSize() > 0) {
                String uniqueFileName = documentService.generateUniqueFileName(file.getOriginalFilename(),
                    UPLOAD_DIRECTORY);
                String filePath = UPLOAD_DIRECTORY + uniqueFileName;
                Path path = Paths.get(filePath);
                Files.write(path, file.getBytes());
                docReport.setDocPath(pathToDownloadUrlService.getAccessUrl(uniqueFileName));
                docReport.setDownloadUrl(pathToDownloadUrlService.getDownloadUrl(uniqueFileName));
            }
            DocReport save = docReportRepository.save(docReport);
            sendDoc.setAction(docReportDTO.getAction());
            sendDoc.setTargetOrganResponse(docReportDTO.getTargetOrganResponse());
            sendDoc.setFindings(docReportDTO.getFindings());
            sendDoc.setDocStatus(docReportDTO.getDocStatus());
            sendDocumentsRepository.save(sendDoc);
            List<Department> department = save.getUser().getDepartment();
            Map<String,Object> details=new HashMap<>();
            details.put("added report title ",save.getReportTitle());
            details.put("report added by user ",save.getUser().getFirstName());
            if (save.getUser().getDepartment().size()==1)
            details.put("report added by department ",save.getUser().getDepartment().get(0).getDepName());
            if (save.getUser().getDepartment().size()>1){
                details.put("report added by department ","committee member");
            }
            ActivityLog activityLog=new ActivityLog();
            String content = activityLog.MapToString(details);
            activityLogService.logsActivity("Document Report", docReport.getDocReportId(), "Created", department,content);
            if (docReport.getDocStatus() != null) {
                sendDoc.setDocStatus(docReport.getDocStatus());
                sendDocumentsRepository.save(sendDoc);
            }
        }

            else {
                throw new IllegalStateException("user or user department is null");
            }

    }

    public Optional<DocReportResponseDTO> getDocReportById(Long id) {
        Optional<DocReport> byId = docReportRepository.findById(id);
        if (byId.isEmpty()) {
            throw new MyNotFoundException("Document Report with Id " + id + " Not Found");
        }
        Long currentUserId = currentUserInfoService.getCurrentUserId();
        List<Long> currentUserDepartmentIds = currentUserInfoService.getCurrentUserDepartmentIds();
        Long userId = byId.get().getUser().getId();
        List<Long> senderDepartmentIds = byId.stream().map(dep -> {
            Long senderDepId = dep.getSendDoc().getSenderDepartment().getDepId();
            return senderDepId;
        }).collect(Collectors.toList());
        List<Long> receiverDepIds = byId.stream().map(receiverDep -> {
            Long collect = receiverDep.getSendDoc().getReceiverDepartment().getDepId();
            return collect;
        }).collect(Collectors.toList());
        Optional<DocReportResponseDTO> docReportResponseDTO = null;
        if (currentUserId == userId || currentUserDepartmentIds.stream().anyMatch(senderDepartmentIds::contains)
                || currentUserDepartmentIds.stream().anyMatch(receiverDepIds::contains)) {
            docReportResponseDTO = byId.map(this::mapEntityToDTO);
        }
        return docReportResponseDTO;
    }

    public List<DocReportResponseDTO> getAllDocReportBySendDocId(Long sendDocId) {
        Long currentUserId = currentUserInfoService.getCurrentUserId();
        List<Long> currentUserDepartmentIds = currentUserInfoService.getCurrentUserDepartmentIds();
        List<DocReport> bysendDocId = docReportRepository.findBysendDocId(sendDocId);
        List<Long> userIds = bysendDocId.stream().map(user -> {
            Long id = user.getUser().getId();
            return id;
        }).collect(Collectors.toList());
        List<Long> senderDepIds = bysendDocId.stream().map(senderDep -> {
            Long depId = senderDep.getSendDoc().getSenderDepartment().getDepId();
            return depId;
        }).collect(Collectors.toList());
        List<Long> receiverDepIds = bysendDocId.stream().map(senderDep -> {
            Long depId = senderDep.getSendDoc().getReceiverDepartment().getDepId();
            return depId;
        }).collect(Collectors.toList());
        List<DocReportResponseDTO> allDocReports = null;
        if (userIds.contains(currentUserId) || currentUserDepartmentIds.stream().anyMatch(senderDepIds::contains)
                || currentUserDepartmentIds.stream().anyMatch(receiverDepIds::contains)) {
            allDocReports = bysendDocId.stream().map(this::mapEntityToDTO)
                    .collect(Collectors.toList());
        }
        return allDocReports;
    }

    private DocReportResponseDTO mapEntityToDTO(DocReport docReport) {
        DocReportResponseDTO map = modelMapper.map(docReport, DocReportResponseDTO.class);
        map.setDepartmentCreatedBy(docReport.getSendDoc().getSenderDepartment().getDepName());
        return map;
    }
}
