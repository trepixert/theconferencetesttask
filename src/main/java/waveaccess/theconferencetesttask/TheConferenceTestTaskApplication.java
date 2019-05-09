package waveaccess.theconferencetesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(value = "waveaccess.theconferencetesttask.models")
public class TheConferenceTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheConferenceTestTaskApplication.class, args);
    }

    /*@Bean
    CommandLineRunner runner(UserRepo userRepo) {
        return args -> {
            userRepo.save(new User("pixert", "qwerty", Collections.singleton(Role.LISTENER)));
            userRepo.save(new User("kontes", "12345", Collections.singleton(Role.PRESENTER)));
            userRepo.save(new User("trepix", "qwert", Collections.singleton(Role.ADMINISTRATOR)));
        };
    }*/
}
