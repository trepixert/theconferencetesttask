package waveaccess.theconferencetesttask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import waveaccess.theconferencetesttask.models.Presentation;
import waveaccess.theconferencetesttask.services.PresentationServiceImpl;

import java.util.List;

public interface PresentationRepo extends JpaRepository<Presentation,Long> {
    /**
     *
     * Честно, так и не понял, почему этот запрос не работает, в консоли базы данных заходит идеально, но
     * здесь почему-то дропается, подключал целый форум джавистов, они тоже ломали голову и не могли понять :D
     * поэтому пришлось убрать отображение авторов презентации в главной странице, если что прошу не кидаться камнями
     * @see "#здесьбылассылканакостыль"
     */
    /*@Query(value = "SELECT PRESENTATIONS.theme, CONFERENCE_USERS.Name, CONFERENCE_USERS.Role \n" +
            "FROM PRESENTATIONS join CONFERENCE_USERS_PRESENTATIONS \n" +
            "on PRESENTATIONS.PRESENTATIONS_ID=CONFERENCE_USERS_PRESENTATIONS.PRESENTATION_ID \n" +
            "join CONFERENCE_USERS \n" +
            "on CONFERENCE_USERS_PRESENTATIONS.USER_ID=CONFERENCE_USERS.conference_users_id and CONFERENCE_USERS.ROLE like 'PRESENTER'",nativeQuery = true)
    List<Presentation> findAllByQuery();*/
    Presentation findPresentationByTheme(String theme);
}
