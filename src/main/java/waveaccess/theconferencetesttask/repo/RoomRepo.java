package waveaccess.theconferencetesttask.repo;

import org.springframework.data.repository.CrudRepository;
import waveaccess.theconferencetesttask.models.Room;

public interface RoomRepo extends CrudRepository<Room,Long> {
}
