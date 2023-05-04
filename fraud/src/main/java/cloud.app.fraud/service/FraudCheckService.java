package cloud.app.fraud.service;

import cloud.app.fraud.model.FraudCheckHistory;
import cloud.app.fraud.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckService (FraudCheckHistoryRepository fraudCheckHistoryRepository){

    public boolean isFraudulentUser(Integer userId){
        fraudCheckHistoryRepository.save(FraudCheckHistory.builder()
                        .isFraudster(false)
                        .userId(userId)
                        .createdAt(LocalDateTime.now())
                .build());
        return false;
    }
}
