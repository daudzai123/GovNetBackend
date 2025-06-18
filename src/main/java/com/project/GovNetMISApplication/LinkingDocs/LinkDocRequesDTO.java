package com.project.GovNetMISApplication.LinkingDocs;

import java.util.List;

import lombok.Data;

@Data
public class LinkDocRequesDTO {
    private Long firstDocId;
    private Long secondDocId = null;
    private List<Long> secondDocIds = null;
}
