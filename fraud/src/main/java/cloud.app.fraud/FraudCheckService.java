package cloud.app.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
