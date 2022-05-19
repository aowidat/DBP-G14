package com.dpb.store.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book extends Product {

    private String binding;
    private int edition;
    private String isbn;
    private int page;
    private LocalDate publication;
    private int weight;
    private int length;
    private int height;
    @ElementCollection
    private List<String> publisher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private List<Person> authors;

    public void addNewAuthor(Person person) {
        if (this.authors != null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(person);
    }

    public void addNewPublisher(String str) {
        if (this.publisher != null) {
            this.publisher = new ArrayList<>();
        }
        this.publisher.add(str);
    }
}
