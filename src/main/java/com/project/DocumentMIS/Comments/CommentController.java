package com.project.DocumentMIS.Comments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController{
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestBody CommentRequestDTO commentRequestDTO){
        try {
            commentService.addComment(commentRequestDTO);
            return ResponseEntity.ok("Comment added successfully");
        }
        catch (HttpClientErrorException.Unauthorized errorException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized to add comment");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/getAllCommentsBySendDocId/{sendDocId}")
    private ResponseEntity<List<CommentResponseDTO>> getAllCommentsBySendDocId(@PathVariable Long sendDocId){
        List<CommentResponseDTO> commentsBySendDocumentId = commentService.getCommentsBySendDocumentId(sendDocId);
        return ResponseEntity.ok(commentsBySendDocumentId);
    }
    // @GetMapping("/getAllCommentsBySendDocId/{sendDocId}")
    // private ResponseEntity<List<CommentResponseDTO>> getAllCommentsBySendDocId(@PathVariable Long sendDocId){
    //     List<CommentResponseDTO> commentsBySendDocumentId = commentService.getCommentsBySendDocumentId(sendDocId);
    //     return ResponseEntity.ok(commentsBySendDocumentId);
    // }
    @GetMapping("/getAllCommentsByParentCommentId/{parentCommentId}")
    public ResponseEntity<List<CommentResponseDTO>> getAllCommentsByParentCommentId(@PathVariable Long parentCommentId){
        List<CommentResponseDTO> commentsByParentCommentId = commentService.getCommentsByParentCommentId(parentCommentId);
        return ResponseEntity.ok(commentsByParentCommentId);
    }
}
