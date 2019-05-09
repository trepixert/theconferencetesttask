package waveaccess.theconferencetesttask.models;

import javax.persistence.*;

@Entity
@Table(name = "Schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
