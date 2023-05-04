package cloud.app.user.service;

import cloud.app.clients.notification.NotificationRequest;
import cloud.app.clients.fraud.FraudCheckResponse;
import cloud.app.clients.fraud.FraudClient;
import cloud.app.clients.notification.NotificationClient;
import cloud.app.user.model.User;
import cloud.app.user.model.UserRegisterRequest;
import cloud.app.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public record UserService(UserRepository userRepository, FraudClient fraudClient, NotificationClient notificationClient) {
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
        // todo: make it async. i.e add to queue
        notificationClient.sendNotification(
                new NotificationRequest(
                        user.getId(),
                        user.getEmail(),
                        String.format("Hi %s, welcome to Amigoscode...", user.getFirstName())
                )
        );
    }
}
