package com.dpb.store.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * CD Entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class CD extends Product {

    private String format;
    private String binding;
    private Integer disc_Nr;
    private LocalDate date;
    @ElementCollection
    @JsonIgnore
    private List<String> tracks;
    @ElementCollection
    @JsonIgnore
    private List<String> labels;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "artist", joinColumns = @JoinColumn(name = "cd_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
    private List<Person> artists;

    /**
     * Method to add a new track to a List of Tracks
     * @param track to be added
     */
    public void addNewTrack(String track) {
        if (this.tracks == null) {
            this.tracks = new ArrayList<>();
        }
        this.tracks.add(track);
    }
    /**
     * Method to add a new label to a List of Labels
     * @param label to be added
     */
    public void addNewLabels(String label) {
        if (this.labels == null) {
            this.labels = new ArrayList<>();
        }
        this.labels.add(label);
    }
    /**
     * Method to add a new Artist to a List of Artists
     * @param person as a Artist to be added
     */
    public void addNewArtist(Person person) {
        if (this.artists == null) {
            this.artists = new ArrayList<>();
        }
        this.artists.add(person);
    }
}
