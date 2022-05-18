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

    @ManyToMany(mappedBy = "actors")
    private Set<DVD> dvds_actors;
    @ManyToMany(mappedBy = "creators")
    private Set<DVD> dvds_creators;
    @ManyToMany(mappedBy = "directors")
    private Set<DVD> dvds_directors;
    @ManyToMany(mappedBy = "artists")
    private Set<CD> cds_artists;
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books_authors;
    @OneToMany(mappedBy = "person")
    private Set<Review> reviews;
}
