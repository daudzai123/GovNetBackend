package com.project.DocumentMIS.APIsForMemberOfCommittee;

import com.project.DocumentMIS.Documents.Enums.DocumentType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/committee")
public class CommitteeController {
    private final CommitteeMembersService committeeMembersService;

    public CommitteeController(CommitteeMembersService committeeMembersService) {
        this.committeeMembersService = committeeMembersService;
    }
    @GetMapping(value = "/getSentDocumentsByDepartmentByType")
    public ResponseDTO getCountOfDocument(){
        ResponseDTO documentsByDocumentType = committeeMembersService.getDocumentsByDocumentType();
        return documentsByDocumentType;
    }
}
