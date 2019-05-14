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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EntityScan(value = "waveaccess.theconferencetesttask.models")
public class TheConferenceTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheConferenceTestTaskApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserRepo userRepo, PresentationRepo presentationRepo) {
        return args -> {
            userRepo.save(new User("admin","admin","admin", new BCryptPasswordEncoder().encode("a"), Role.ADMINISTRATOR));
            userRepo.save(new User("presenter","presenter","presenter", new BCryptPasswordEncoder().encode("p"), Role.PRESENTER));
            userRepo.save(new User("listener","listener","listener", new BCryptPasswordEncoder().encode("l"), Role.LISTENER));
            userRepo.save(new User("presenter2","presenter2","presenter2", new BCryptPasswordEncoder().encode("l"), Role.PRESENTER));
            userRepo.save(new User("listener2","listener2", "listener2", new BCryptPasswordEncoder().encode("l"), Role.LISTENER));
            userRepo.save(new User("listener3","listener3","listener3", new BCryptPasswordEncoder().encode("l"), Role.LISTENER));

            User author = userRepo.findUserByUsername("presenter");
            User author2 = userRepo.findUserByUsername("presenter2");

            presentationRepo.save(new Presentation("JTI Compilation","2019-05-15 11:30", Room.OFFICE));
            Presentation presentationByTheme = presentationRepo.findPresentationByTheme("JTI Compilation");
            presentationByTheme.addAuthor(author);
            presentationRepo.save(presentationByTheme);
            author.addPresentation(presentationByTheme);

            presentationRepo.save(new Presentation("Optimization", "2019-05-17 11:30", Room.HALL));
            presentationByTheme = presentationRepo.findPresentationByTheme("Optimization");
            presentationByTheme.addAuthor(author2);
            presentationRepo.save(presentationByTheme);
            author2.addPresentation(presentationByTheme);

            presentationRepo.save(new Presentation("Spring Boot","2019-05-18 11:30", Room.AUDIENCE));
            presentationByTheme = presentationRepo.findPresentationByTheme("Spring Boot");
            presentationByTheme.addAuthor(author);
            presentationByTheme.addAuthor(author2);
            presentationRepo.save(presentationByTheme);
            author.addPresentation(presentationByTheme);
            author2.addPresentation(presentationByTheme);

            presentationRepo.save(new Presentation("Gradle","2019-05-19 11:30", Room.HALL));
            presentationByTheme = presentationRepo.findPresentationByTheme("Gradle");
            presentationByTheme.addAuthor(author);
            presentationByTheme.addAuthor(author2);
            presentationRepo.save(presentationByTheme);
            author.addPresentation(presentationByTheme);
            author2.addPresentation(presentationByTheme);

            presentationRepo.save(new Presentation("Maven", "2019-05-20 11:30", Room.OFFICE));
            presentationByTheme = presentationRepo.findPresentationByTheme("Maven");
            presentationByTheme.addAuthor(author2);
            presentationRepo.save(presentationByTheme);
            author2.addPresentation(presentationByTheme);

            userRepo.save(author);
            userRepo.save(author2);
        };
    }
}
