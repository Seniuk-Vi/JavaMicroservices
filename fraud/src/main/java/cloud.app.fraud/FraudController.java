package cloud.app.fraud;

import cloud.app.clients.fraud.FraudCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public record FraudController(FraudCheckService fraudCheckService) {
    @GetMapping(path = "{userId}")
    public FraudCheckResponse isFraudster(@PathVariable("userId") Integer userId){
        log.info("fraud check request for user {}", userId);
        boolean isFraudulentUser = fraudCheckService.isFraudulentUser(userId);
        return new FraudCheckResponse(isFraudulentUser);
    }
}
