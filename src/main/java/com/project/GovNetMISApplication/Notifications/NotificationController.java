package com.project.GovNetMISApplication.Notifications;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.GovNetMISApplication.user.CurrentUserInfoService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;
    @Autowired
    CurrentUserInfoService currentUserInfoService;
    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/byUserId/{docId}")
    public List<NotificationRespondDTO> getSentDocsByDocId(@PathVariable Long docId){
        return notificationService.getUnreadNotificationsForUser(docId);
    }

    @GetMapping("/allOfUser")
    public List<NotificationRespondDTO> getSentDocsByDocId(){
        Long userId = currentUserInfoService.getCurrentUserId();
        return notificationService.getUnreadNotificationsForUser(userId);
    }

    @GetMapping("/all")
    public List<NotificationRespondDTO> getAll(){
        return notificationService.allNotifications();
    }

    @GetMapping("/markAsRead/{notificationId}")
    public ResponseEntity<String> markNotificationAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok().body("The Notification is mareked as read.");
    }

    @GetMapping("/markAllAsRead/{userId}")
    public ResponseEntity<String> markAllAsRead(@PathVariable Long userId) {
        notificationService.markAllAsRead(userId);
        return ResponseEntity.ok().body("The Notification is mareked as read.");
    }



}
