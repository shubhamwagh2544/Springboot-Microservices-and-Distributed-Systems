package com.tigercodes.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_seq"
    )
    @SequenceGenerator(
            name = "notification_id_seq",
            sequenceName = "notification_id_seq",
            allocationSize = 1
    )
    private Integer notificationId;
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String sender;
    private String message;
    private LocalDateTime sentAt;
}
