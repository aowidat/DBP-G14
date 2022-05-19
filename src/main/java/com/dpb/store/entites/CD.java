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
public class CD extends Product {

    private String format;
    private String binding;
    private int disc_Nr;
    private LocalDate date;
    @ElementCollection
    private List<String> tracks;
    @ElementCollection
    private List<String> labels;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "artist", joinColumns = @JoinColumn(name = "cd_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private List<Person> artists;

    public void addNewTrack(String str) {
        if (this.tracks == null) {
            this.tracks = new ArrayList<>();
        }
        this.tracks.add(str);
    }

    public void addNewLabels(String str) {
        if (this.labels == null) {
            this.labels = new ArrayList<>();
        }
        this.labels.add(str);
    }

    public void addNewArtist(Person pr) {
        if (this.artists == null) {
            this.artists = new ArrayList<>();
        }
        this.artists.add(pr);
    }
}
