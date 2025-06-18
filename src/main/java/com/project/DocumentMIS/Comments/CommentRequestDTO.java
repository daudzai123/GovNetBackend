package com.project.DocumentMIS.Comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {
    private Long commentId;
    private String commentText;
    private Long sendDocId;
    private Long parentCommentId;
}
