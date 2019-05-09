package waveaccess.theconferencetesttask.repo;

import org.springframework.data.repository.CrudRepository;
import waveaccess.theconferencetesttask.models.Presentation;

public interface PresentationRepo extends CrudRepository<Presentation,Long> {
}
