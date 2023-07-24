package com.tigercodes.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory
                        .builder()
                        .id(customerId)
                        .isFraudster(false)                 // we can do some checks for this
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
