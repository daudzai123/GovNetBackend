package com.project.GovNetMISApplication.APIsForMemberOfCommittee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
