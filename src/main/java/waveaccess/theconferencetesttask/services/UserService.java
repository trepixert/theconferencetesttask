package waveaccess.theconferencetesttask.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import waveaccess.theconferencetesttask.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    Optional<User> findById(long id);
    void save(User user);
    User findUserByUsername(String username);
    void delete(User user);
}
