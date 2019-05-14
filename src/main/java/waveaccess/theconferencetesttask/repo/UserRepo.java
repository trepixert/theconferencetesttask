package waveaccess.theconferencetesttask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waveaccess.theconferencetesttask.models.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
}
