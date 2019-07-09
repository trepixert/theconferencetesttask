package waveaccess.theconferencetesttask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import waveaccess.theconferencetesttask.models.Presentation;

import java.util.List;

public interface PresentationRepo extends JpaRepository<Presentation,Long> {

    @Query(value = "SELECT DISTINCT PRESENTATIONS.id, PRESENTATIONS.theme,PRESENTATIONS.date_and_time, PRESENTATIONS.room, CONFERENCE_USERS.Role \n" +
            "FROM PRESENTATIONS join CONFERENCE_USERS_PRESENTATIONS \n" +
            "on PRESENTATIONS.id=CONFERENCE_USERS_PRESENTATIONS.PRESENTATION_ID \n" +
            "join CONFERENCE_USERS \n" +
            "on CONFERENCE_USERS_PRESENTATIONS.USER_ID=CONFERENCE_USERS.id and CONFERENCE_USERS.ROLE like 'PRESENTER'",nativeQuery = true)
    List<Presentation> findAllByQuery();
    Presentation findPresentationByTheme(String theme);
}
