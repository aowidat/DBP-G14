package com.dpb.store.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
    private Date release_date;
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
}
