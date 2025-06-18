package com.project.DocumentMIS.LinkingDocs;

import java.util.List;

import lombok.Data;

@Data
public class LinkDocRequesDTO {
    private Long firstDocId;
    private Long secondDocId = null;
    private List<Long> secondDocIds = null;
}
