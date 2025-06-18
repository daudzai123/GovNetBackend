package com.project.DocumentMIS.Notifications;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.DocumentMIS.ExceptionHandlingFiles.MyNotFoundException;


@Service
public class NotificationService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    NotificationRepository notificationRepository;

    
    public List<NotificationRespondDTO> getUnreadNotificationsForUser(Long id) {
        List<Notification> notifications = notificationRepository.findByUserId(id);
        List<NotificationRespondDTO> data = notifications.stream().map(noti -> {
            NotificationRespondDTO respondDTO = modelMapper.map(noti, NotificationRespondDTO.class);
            respondDTO.setContent(noti.getDataMap());
            return respondDTO;
        }).collect(Collectors.toList());
        return data;
    }
    public List<NotificationRespondDTO> allNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        List<NotificationRespondDTO> data = notifications.stream().map(noti -> {
            NotificationRespondDTO respondDTO = modelMapper.map(noti, NotificationRespondDTO.class);
            respondDTO.setContent(noti.getDataMap());
            return respondDTO;
        }).collect(Collectors.toList());
        return data;
    }

     public Boolean markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
            .orElseThrow(() -> new MyNotFoundException("Notification not found"));
        notification.setRead_at(LocalDateTime.now());
        notificationRepository.save(notification);
        return true;
    
    }
    public void markAllAsRead(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        for (Notification notification : notifications) {
            notification.setRead_at(LocalDateTime.now());
            notificationRepository.save(notification);
        }
    }

}
