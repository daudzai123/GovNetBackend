package com.project.DocumentMIS.AppendantDocuments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppendantDocService {
    @Autowired
    private AppendantDocRepository appendantDocRepo;
//    public List<appendantDocs> appendantDocsByDocumentId(Long docId){
//        return appendantDocRepo.findByDocumentId(docId);
//    }
}

