package cloud.app.user;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record UserService(UserRepository userRepository, RestTemplate restTemplate) {
    public void registerUser(UserRegisterRequest userRegisterRequest) {
        User user = User.builder()
                .firstName(userRegisterRequest.firstName())
                .lastName(userRegisterRequest.lastName())
                .email(userRegisterRequest.email())
                .build();
        // todo: check if email valid
        // todo: check if email unique
        userRepository.saveAndFlush(user);

        // todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{userId}",
                FraudCheckResponse.class,
                user.getId()
        );
        assert fraudCheckResponse != null;
        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
        // todo: send notification
    }
}
