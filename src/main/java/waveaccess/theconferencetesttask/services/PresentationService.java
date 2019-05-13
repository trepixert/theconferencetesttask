package waveaccess.theconferencetesttask.services;

import waveaccess.theconferencetesttask.models.Presentation;

import java.util.List;
import java.util.Optional;

public interface PresentationService {
    Optional<Presentation> findById(Long id);
    List<Presentation> findAll();
    void save(Presentation presentation);
    Presentation findPresentationByTheme(String theme);
    void delete(Presentation presentation);
}
