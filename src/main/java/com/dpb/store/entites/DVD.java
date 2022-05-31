package com.dpb.store.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DVD Entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class DVD extends  Product {

    private String format;
    private String aspectratio;
    private int regioncode;
    private LocalDate release_date;
    private int running_time;
    private int theater_release;
    @ElementCollection
    private List<String> studio;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "actor" , joinColumns = @JoinColumn(name ="dvd_id" , referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> actors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "creator" , joinColumns = @JoinColumn(name ="dvd_id" , referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> creators;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dirctor" , joinColumns = @JoinColumn(name ="dvd_id" , referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> directors;
    /**
     * Method to add a new studio to a List of studios
     * @param studio to be added
     */
    public void addNewStudio(String studio){
        if(this.studio==null){
            this.studio= new ArrayList<>();
        }
        this.studio.add(studio);
    }
    /**
     * Method to add a new actor to a List of actors
     * @param actor to be added
     */
    public void addNewActor(Person actor) {
        if (this.actors==null){
            this.actors = new HashSet<>();
        }
        this.actors.add(actor);
    }
    /**
     * Method to add a new creator to a List of creators
     * @param creator to be added
     */
    public void addNewCreator(Person creator) {
        if (this.creators==null){
            this.creators = new HashSet<>();
        }
        this.creators.add(creator);
    }
    /**
     * Method to add a new director to a List of directors
     * @param director to be added
     */
    public void addNewDirector(Person director) {
        if (this.directors==null){
            this.directors = new HashSet<>();
        }
        this.directors.add(director);
    }
}
