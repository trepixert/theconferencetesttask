package waveaccess.theconferencetesttask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import waveaccess.theconferencetesttask.models.Presentation;
import waveaccess.theconferencetesttask.models.Role;
import waveaccess.theconferencetesttask.models.Room;
import waveaccess.theconferencetesttask.models.User;
import waveaccess.theconferencetesttask.repo.PresentationRepo;
import waveaccess.theconferencetesttask.repo.UserRepo;

@SpringBootApplication
@EntityScan(value = "waveaccess.theconferencetesttask.models")
public class TheConferenceTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheConferenceTestTaskApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserRepo userRepo, PresentationRepo presentationRepo) {
        return args -> {
            userRepo.save(new User("tukTukNeo","Mister","Anderson", new BCryptPasswordEncoder().encode("followthewhiterabbit"), Role.ADMINISTRATOR));
            userRepo.save(new User("resu","someName","someSurname", new BCryptPasswordEncoder().encode("12345"), Role.PRESENTER));
            userRepo.save(new User("lamer","w","a", new BCryptPasswordEncoder().encode("qwerty"), Role.LISTENER));
            userRepo.save(new User("someguy","v","e", new BCryptPasswordEncoder().encode("qwerty"), Role.LISTENER));
            userRepo.save(new User("idontknow","a", "c", new BCryptPasswordEncoder().encode("qwerty"), Role.LISTENER));
            userRepo.save(new User("nepridumal","ce","ss", new BCryptPasswordEncoder().encode("qwerty"), Role.LISTENER));
            presentationRepo.save(new Presentation("JTI Compilation","2019-05-16 11:30", Room.OFFICE));
            presentationRepo.save(new Presentation("JTI Compilation","2019-05-16 11:30", Room.HALL));
            presentationRepo.save(new Presentation("JTI Compilation","2019-05-16 11:30", Room.AUDIENCE));
            presentationRepo.save(new Presentation("JTI Compilation","2019-05-16 11:30", Room.HALL));
            presentationRepo.save(new Presentation("JTI Compilation","2019-05-16 11:30", Room.OFFICE));
            presentationRepo.save(new Presentation("JTI Compilation","2019-05-16 11:30", Room.AUDIENCE));
        };
    }
}
