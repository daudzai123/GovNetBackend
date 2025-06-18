package com.project.GovNetMISApplication.LinkingDocs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.GovNetMISApplication.Documents.Document;
import com.project.GovNetMISApplication.Documents.DocumentRepository;
import com.project.GovNetMISApplication.sendDocuments.SendDoc;
import com.project.GovNetMISApplication.sendDocuments.SendDocumentsRepository;
import com.project.GovNetMISApplication.user.CurrentUserInfoService;

@Service
public class LinkingDocService {
    @Autowired
    private LinkingDocRepository linkingDocRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired 
    private CurrentUserInfoService currentUserInfoService;
    @Autowired 
    private SendDocumentsRepository sendDocumentsRepository;
    public List<Long> findLinkedDocuments(Long id) {
        List<LinkingDoc> linkingDocs = linkingDocRepository.findByFirstDocOrSecondDoc(id);
        List<Long> docIds = new ArrayList<>();
        for (LinkingDoc linkingDoc : linkingDocs) {
            if (!docIds.contains(linkingDoc.getFirst())) {
                docIds.add(linkingDoc.getFirst());
            }
            if (!docIds.contains(linkingDoc.getSecond())) {
                docIds.add(linkingDoc.getSecond());
            }
        }
        if (docIds.contains(id)) {
            docIds.remove(id);
        }
        return docIds;
    }

    public List<LinkDocDTO> findDocFromSenDoc(Long id) {
        List<Long> docIds = findLinkedDocuments(id);
        Long departmentId = currentUserInfoService.getCurrentUserDepartments().get(0).getDepId();
        List<SendDoc> linkedSenDocs = sendDocumentsRepository.findAllByDocIds(docIds,departmentId);
        return mapEntityToDTO(linkedSenDocs);
    }

    public List<LinkDocDTO> findlinkedDocs(Long id) {
        List<Long> docIds = findLinkedDocuments(id);
        List<Document> linkedDocs = documentRepository.findAllById(docIds);
        return mapEntityToDTO1(linkedDocs);
    }

    private List<LinkDocDTO> mapEntityToDTO(List<SendDoc> sentDocumentByDepartments) {
        List<LinkDocDTO> sendDocResponseDTOS = sentDocumentByDepartments.stream()
            .map(doc -> {
                LinkDocDTO map = modelMapper.map(doc, LinkDocDTO.class);
                if (doc.getDocumentId().getReferenceType() != null) {
                    map.setReferenceType(doc.getDocumentId().getReferenceType());
                }
                map.setSendDocId(doc.getSendDocId());
                map.setOwnerDep(false);
                if (doc.getDocumentId() != null) {
                    Document u = doc.getDocumentId();
                    map.setDocNumber(u.getDocNumber());
                    map.setDocNumber2(u.getDocNumber2());
                    map.setInitialDate(u.getInitialDate());
                    map.setSecondDate(u.getSecondDate());
                    map.setSubject(u.getSubject());
                    map.setDocId(u.getDocId());
                    map.setDocType(u.getDocType());
                }
                map.setOwnerDep(false);
                return map;
            })
            .collect(Collectors.toList());
        return sendDocResponseDTOS;
    }

    private List<LinkDocDTO> mapEntityToDTO1(List<Document> sentDocumentByDepartments) {
        List<LinkDocDTO> sendDocResponseDTOS = sentDocumentByDepartments.stream()
            .map(doc -> {
                LinkDocDTO map = modelMapper.map(doc, LinkDocDTO.class);
                if (doc.getReferenceType() != null) {
                    map.setReferenceType(doc.getReferenceType());
                }
                map.setSendDocId(null);
                map.setOwnerDep(true);
                map.setDocNumber(doc.getDocNumber());
                map.setDocNumber2(doc.getDocNumber2());
                map.setInitialDate(doc.getInitialDate());
                map.setSecondDate(doc.getSecondDate());
                map.setSubject(doc.getSubject());
                map.setDocId(doc.getDocId());
                map.setDocType(doc.getDocType());
                map.setOwnerDep(true);
                return map;
            })
            .collect(Collectors.toList());
        return sendDocResponseDTOS;
    }

    public void linkDocuments(Long first, Long second) {
        LinkingDoc link = new LinkingDoc();
        link.setFirst(first);
        link.setSecond(second);
        link.setDepartment(currentUserInfoService.getCurrentUserDepartments().get(0));
        link.setUser(currentUserInfoService.getCurrentUser());
        link.setCreateDate(LocalDate.now());
        linkingDocRepository.save(link);
    }

    public void deletelinks(Long id) {
        linkingDocRepository.deleteAllByFirst(id);
    }

    public void deleteById(Long id) {
        linkingDocRepository.deleteById(id);
    }
}
