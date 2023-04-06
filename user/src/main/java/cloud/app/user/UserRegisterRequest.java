package cloud.app.user;

public record UserRegisterRequest(
        String firstName,
        String lastName,
        String email) {

}
