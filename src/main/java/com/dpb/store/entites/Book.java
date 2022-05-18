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
public class Book extends  Product {

    private String binding;
    private int edition;
    private String isbn;
    private int page;
    private Date publication;
    private int weight;
    private int length;
    private int height;
    @ElementCollection
    private List<String> publiher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author" , joinColumns = @JoinColumn(name ="book_id" , referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> authors;
}
