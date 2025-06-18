package com.project.DocumentMIS.Notifications;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    // List<Notification> findByRecipientAndReadAtIsNull(Users recipient);
    @Query("SELECT n FROM Notification n WHERE n.recipient.id = :userId AND read_at IS NULL")
    List<Notification> findByUserId(@Param("userId") Long userId);
}
