package com.dpb.store.entites;

import com.dpb.store.services.other.ContactPerson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Person Entity to be extended as Author, Actor...ect
 */
@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Embedded
    private ContactPerson adress;


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
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Review> reviews;
    @OneToMany(mappedBy = "person")
    private List<Order> orders = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return getName() != null ? getName().equals(person.getName()) : person.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
