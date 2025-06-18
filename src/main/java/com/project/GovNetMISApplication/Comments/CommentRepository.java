package com.project.GovNetMISApplication.Comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("SELECT d FROM Comment d WHERE d.sendDocId.id = :userId")
    List<Comment> findAllBySendDocId(@Param("userId") Long userId);
    // List<Comment> findAllBySendDocId(Long sendDocId);
    List<Comment> findAllByParentCommentIdCommentId(Long commentId);

}
