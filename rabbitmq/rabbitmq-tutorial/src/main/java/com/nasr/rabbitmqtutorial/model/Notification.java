package com.nasr.rabbitmqtutorial.model;

import com.nasr.rabbitmqtutorial.enumeration.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    private String content;
    private String to;
    private NotificationType type;

    @Override
    public String toString() {
        return "Notification{" +
                "content='" + content + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
