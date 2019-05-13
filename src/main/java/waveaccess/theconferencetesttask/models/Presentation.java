package waveaccess.theconferencetesttask.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Presentations")
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long presentations_id;
    private String theme;

    @ManyToMany(mappedBy = "presentations")
    private List<User> author;

    @Column(name = "Date_and_time", unique = true)
    private String dateTimeFormat;

    @Enumerated(EnumType.STRING)
    private Room room;

    public Presentation() {
    }

    public Presentation(String theme, String dateTimeFormat, Room room) {
        this.theme = theme;
        this.dateTimeFormat = dateTimeFormat;
        this.room = room;
    }

    public Long getId() {
        return presentations_id;
    }

    public void setId(Long id) {
        this.presentations_id = id;
    }

    public List<User> getAuthor() {
        return author;
    }

    public void setAuthor(List<User> author) {
        this.author = author;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void addAuthor(User author){
        this.author.add(author);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }
}
