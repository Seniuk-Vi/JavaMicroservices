package cloud.app.user.model;

public record UserRegisterRequest(
        String firstName,
        String lastName,
        String email) {

}
