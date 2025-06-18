package com.project.GovNetMISApplication.Comments;

import lombok.*;
@Setter
@Getter
public class ParentComment {
    private String comment;
    private String commenter;
    private Long parentId;
}