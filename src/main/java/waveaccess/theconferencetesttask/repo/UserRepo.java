package waveaccess.theconferencetesttask.repo;

import org.springframework.data.repository.CrudRepository;
import waveaccess.theconferencetesttask.models.User;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> {
    Optional<User> findUserByUsername(String username);
}
