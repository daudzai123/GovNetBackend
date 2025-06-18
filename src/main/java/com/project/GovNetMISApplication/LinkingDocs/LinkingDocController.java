package com.project.GovNetMISApplication.LinkingDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/linking")
public class LinkingDocController {
    @Autowired
    private LinkingDocService linkingDocService;

    @PostMapping("/add")
    public String addLinkingDocs(@RequestBody LinkDocRequesDTO requesDTO) {
        if (requesDTO.getSecondDocId()!=null) {
            linkingDocService.linkDocuments(requesDTO.getFirstDocId(), requesDTO.getSecondDocId());
        }
        if (requesDTO.getSecondDocIds() != null && requesDTO.getSecondDocIds().size()>0) {
            for (Long secondId : requesDTO.getSecondDocIds()) {
                linkingDocService.linkDocuments(requesDTO.getFirstDocId(), secondId);
            }
        }
        return "Documents are linked successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLinking(@PathVariable Long id) {
        try {
             linkingDocService.deleteById(id);
             return "the documents are unlinked.";
        } catch (Exception e) {
            return "the there was a problem to unlink the docs.";
        }
        
    }
}