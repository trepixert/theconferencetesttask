package waveaccess.theconferencetesttask.repo;

import org.springframework.data.repository.CrudRepository;
import waveaccess.theconferencetesttask.models.Schedule;

public interface ScheduleRepo extends CrudRepository<Schedule,Long> {
}
