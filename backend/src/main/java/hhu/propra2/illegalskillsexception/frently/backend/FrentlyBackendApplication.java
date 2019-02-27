package hhu.propra2.illegalskillsexception.frently.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableRetry
@SpringBootApplication
public class FrentlyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrentlyBackendApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

