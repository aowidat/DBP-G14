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


    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "actor" , joinColumns = @JoinColumn(name ="dvd_id" , referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> actors;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "creator" , joinColumns = @JoinColumn(name ="dvd_id" , referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> creators;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "dirctor" , joinColumns = @JoinColumn(name ="dvd_id" , referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> directors;

    public void addNewStudio(String str){
        if(this.studio==null){
            this.studio= new ArrayList<>();
        }
        this.studio.add(str);
    }

    public void addNewActor(Person person) {
        if (this.actors==null){
            this.actors = new HashSet<>();
        }
        this.actors.add(person);
    }

    public void addNewCreator(Person person) {
        if (this.creators==null){
            this.creators = new HashSet<>();
        }
        this.creators.add(person);
    }

    public void addNewDirector(Person person) {
        if (this.directors==null){
            this.directors = new HashSet<>();
        }
        this.directors.add(person);
    }
}
