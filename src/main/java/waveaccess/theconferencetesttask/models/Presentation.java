package waveaccess.theconferencetesttask.models;

import javax.persistence.*;

@Entity
@Table(name = "Presentations")
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
