package com.project.DocumentMIS.Notifications;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class NotificationRespondDTO {
    private Long Id;
    private String notificationType;
    private LocalDateTime created_at;
    private Map<String, Object> content;

}
