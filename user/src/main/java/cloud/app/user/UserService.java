package cloud.app.user;

import cloud.app.clients.fraud.FraudCheckResponse;
import cloud.app.clients.fraud.FraudClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record UserService(UserRepository userRepository, FraudClient fraudClient) {
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
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(user.getId());
        assert fraudCheckResponse != null;
        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
        // todo: send notification
    }
}
