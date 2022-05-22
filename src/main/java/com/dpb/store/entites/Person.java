package com.dpb.store.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "actors", cascade = {CascadeType.MERGE})
    private Set<DVD> dvds_actors;
    @ManyToMany(mappedBy = "creators", cascade = {CascadeType.MERGE})
    private Set<DVD> dvds_creators;
    @ManyToMany(mappedBy = "directors", cascade = {CascadeType.MERGE})
    private Set<DVD> dvds_directors;
    @ManyToMany(mappedBy = "artists", cascade = {CascadeType.MERGE})
    private Set<CD> cds_artists;
    @ManyToMany(mappedBy = "authors", cascade = {CascadeType.MERGE})
    private Set<Book> books_authors;
    @OneToMany(mappedBy = "person", cascade = {CascadeType.MERGE})
    private Set<Review> reviews;
}
