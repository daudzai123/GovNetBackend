package com.project.DocumentMIS.Comments;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private String commentText;
    private LocalDateTime commentDateTime;
    private String commenterName;
    private String commenterDepartment;
    private String depName;
    private String parentText;
    private  String parent_commenter;
    private Long parentId;
//    private parentCommentResponseDTO parentComment;
}
