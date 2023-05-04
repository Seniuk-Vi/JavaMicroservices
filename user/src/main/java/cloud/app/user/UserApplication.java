package cloud.app.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication(
        scanBasePackages = {
                "cloud.app.user",
                "cloud.app.amqp",
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "cloud.app.clients"
)
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
