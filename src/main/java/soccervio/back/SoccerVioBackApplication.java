package soccervio.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SoccerVioBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoccerVioBackApplication.class, args);
    }

}
