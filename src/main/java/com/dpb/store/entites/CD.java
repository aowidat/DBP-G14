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
public class CD extends Product {

    private String format;
    private String binding;
    private int disc_Nr;
    private Date date;
    @ElementCollection
    private List<String> tracks;
    @ElementCollection
    private List<String> labels;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "artist" , joinColumns = @JoinColumn(name ="cd_id" , referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private Set<Person> artists;
}
