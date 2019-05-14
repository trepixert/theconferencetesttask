package waveaccess.theconferencetesttask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waveaccess.theconferencetesttask.models.Presentation;
import waveaccess.theconferencetesttask.models.Role;
import waveaccess.theconferencetesttask.models.User;
import waveaccess.theconferencetesttask.repo.PresentationRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PresentationServiceImpl implements PresentationService {

    @Autowired
    private PresentationRepo presentationRepo;

    @Override
    public Optional<Presentation> findById(Long id) {
        return presentationRepo.findById(id);
    }

    @Override
    public List<Presentation> findAll() {
        return presentationRepo.findAllByQuery();
    }


    @Override
    public void save(Presentation presentation) {
        presentationRepo.save(presentation);
    }

    @Override
    public Presentation findPresentationByTheme(String theme) {
        return presentationRepo.findPresentationByTheme(theme);
    }

    @Override
    public void delete(Presentation presentation) {
        presentationRepo.delete(presentation);
    }
}
