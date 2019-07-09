package waveaccess.theconferencetesttask.services;

import waveaccess.theconferencetesttask.models.Presentation;
import waveaccess.theconferencetesttask.models.User;

import java.util.List;
import java.util.Optional;

public interface PresentationService {
    Optional<Presentation> findById(Long id);
    List<Presentation> findAll();
    void save(Presentation presentation, String date, List<User> author);
    Presentation findPresentationByTheme(String theme);
    void delete(Presentation presentation);
}
