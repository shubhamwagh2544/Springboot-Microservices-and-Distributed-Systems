package com.tigercodes.fraud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FraudCheckHistory {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_seq"
    )
    @SequenceGenerator(
            name = "fraud_id_seq",
            sequenceName = "fraud_id_seq",
            allocationSize = 1
    )
    private Integer id;
    private Integer customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;
}
