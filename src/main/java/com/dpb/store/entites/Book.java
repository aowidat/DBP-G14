package com.dpb.store.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Book Entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book extends Product {

    private String binding;
    private String edition;
    private String isbn;
    private Integer page;
    private LocalDate publication;
    private Integer weight;
    private Integer length;
    private Integer height;
    @ElementCollection
    @JsonIgnore
    private List<String> publisher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "author", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private List<Person> authors;

    /**
     * Method to add an Author to the list of Authors
     * @param person to be added
     */
    public void addNewAuthor(Person person) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(person);
    }
    /**
     * Method to add an publisher to the list of Publishers
     * @param publisher to be added
     */
    public void addNewPublisher(String publisher) {
        if (this.publisher == null) {
            this.publisher = new ArrayList<>();
        }
        this.publisher.add(publisher);
    }
}
