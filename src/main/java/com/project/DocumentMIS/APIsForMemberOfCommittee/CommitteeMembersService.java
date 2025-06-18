package com.project.DocumentMIS.APIsForMemberOfCommittee;

import com.project.DocumentMIS.Documents.DocumentRepository;
import com.project.DocumentMIS.Documents.Enums.DocumentType;
import com.project.DocumentMIS.sendDocuments.Enums.documentStatus;
import com.project.DocumentMIS.sendDocuments.SendDocumentsRepository;
import com.project.DocumentMIS.user.CurrentUserInfoService;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommitteeMembersService {
    private final DocumentRepository documentRepository;
    private final SendDocumentsRepository sendDocumentsRepository;
    private final CurrentUserInfoService currentUserInfoService;

    public CommitteeMembersService(DocumentRepository documentRepository, SendDocumentsRepository sendDocumentsRepository, CurrentUserInfoService currentUserInfoService) {
        this.documentRepository = documentRepository;
        this.sendDocumentsRepository = sendDocumentsRepository;
        this.currentUserInfoService = currentUserInfoService;
    }

    public ResponseDTO getDocumentsByDocumentType(){
        Long currentUserId = currentUserInfoService.getCurrentUserId();
        List<Long> currentUserDepartmentIds = currentUserInfoService.getCurrentUserDepartmentIds();
//        Long count=sendDocumentsRepository.findSentDocumentsByDepartmentsOfTypeAhkaam(currentUserId, currentUserDepartmentIds);
        List<Object[]> resultList = sendDocumentsRepository.countSentDocumentsByDepartmentsAndDocType(currentUserId, currentUserDepartmentIds);
        Map<DocumentType,Long> result=new HashMap<>();
        for (Object[] row : resultList) {
            DocumentType docType = (DocumentType) row[0];
            Long count = (Long) row[1];
            result.put(docType, count);
        }
        List<Object[]> objects = sendDocumentsRepository.countSentDocumentsByDepartmentsAndDocTypeAndStatus(currentUserId, currentUserDepartmentIds);
        // Initialize the result map
        Map<DocumentType, Map<documentStatus, Long>> resultMap = new HashMap<>();

// Iterate through the result list
        for (Object[] row : objects) {
            // Extract values from the result array
            DocumentType documentType = (DocumentType) row[0];
            documentStatus documentStatus = (documentStatus) row[1];
            Long count = (Long) row[2];

            // Populate the resultMap
            resultMap
                    .computeIfAbsent(documentType, k -> new HashMap<>())
                    .put(documentStatus, count);
        }
        List<Object[]> receivedDocumentByDepartmentsByDocType = sendDocumentsRepository.findReceivedDocumentByDepartmentsByDocType(currentUserDepartmentIds);
        Map<DocumentType,Long> resultForReceived =new HashMap<>();
        for (Object[] row:receivedDocumentByDepartmentsByDocType
             ) {
            DocumentType documentType=(DocumentType) row[0];
            Long count=(Long) row[1];
            resultForReceived.put(documentType,count);
        }
        List<Object[]> receivedDocumentByDepartmentsByDocTypeAndStatus = sendDocumentsRepository.findReceivedDocumentByDepartmentsByDocTypeAndStatus(currentUserDepartmentIds);
        Map<DocumentType,Map<documentStatus,Long>> resultForReceivedByDocTypeAndStatus=new HashMap<>();
        for (Object[] row:receivedDocumentByDepartmentsByDocTypeAndStatus
             ) {
            DocumentType documentType=(DocumentType) row[0];
            documentStatus documentStatuses=(documentStatus) row[1];
            Long count=(Long) row[2];
            resultForReceivedByDocTypeAndStatus
                    .computeIfAbsent(documentType,k->new HashMap<>())
                    .put(documentStatuses,count);
        }
        return new ResponseDTO(result,resultMap,resultForReceived,resultForReceivedByDocTypeAndStatus);
    }
}
