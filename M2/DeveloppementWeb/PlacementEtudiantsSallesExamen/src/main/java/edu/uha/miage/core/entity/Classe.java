package edu.uha.miage.core.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String name;

    @OneToOne
    private Place place;

    public Classe(String name, Place place) {
        this.name = name;
        this.place = place;
    }

    public Classe() {

    }
}
