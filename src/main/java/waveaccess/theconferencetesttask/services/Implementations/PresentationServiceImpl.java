package waveaccess.theconferencetesttask.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waveaccess.theconferencetesttask.models.Presentation;
import waveaccess.theconferencetesttask.models.User;
import waveaccess.theconferencetesttask.repo.PresentationRepo;
import waveaccess.theconferencetesttask.repo.UserRepo;
import waveaccess.theconferencetesttask.services.PresentationService;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PresentationServiceImpl implements PresentationService {

    @Autowired
    private PresentationRepo presentationRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Optional<Presentation> findById(Long id) {
        return presentationRepo.findById(id);
    }

    @Override
    public List<Presentation> findAll() {
        return presentationRepo.findAllByQuery();
    }


    @Override
    public void save(Presentation presentation, String date, List<User> author) {
        author.forEach(presentation::addAuthor);
        date = date.replaceAll("T"," ");
        presentation.setDateTimeFormat(date);
        presentationRepo.save(presentation);
        author.forEach(user -> {
            user.addPresentation(presentation);
            userRepo.save(user);
        });
    }

    @Override
    public Presentation findPresentationByTheme(String theme) {
        return presentationRepo.findPresentationByTheme(theme);
    }

    @Override
    public void delete(Presentation presentation) {
        Iterator<User> i = presentation.getAuthor().iterator();
        while(i.hasNext()) {
            User author = i.next();
            author.getPresentations().remove(presentation);
            i.remove();
        }
        presentationRepo.delete(presentation);
    }
}
