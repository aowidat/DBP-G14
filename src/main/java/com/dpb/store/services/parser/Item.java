package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Item {
    private String asin;
    private String ean;
    private String pgroup;
    private String title;
    private String salesrank;
    @JacksonXmlElementWrapper(localName = "price", useWrapping = false)
    private Price price;
    private Detail details;
    @JacksonXmlElementWrapper
    private List<Item> similars = new ArrayList<>();
    @JacksonXmlText
    private String value;

    private Bookspec bookspec;
    @JacksonXmlElementWrapper
    private List<GeneralField> authors = new ArrayList<>();
    @JacksonXmlElementWrapper
    private List<GeneralField> publishers = new ArrayList<>();
    @JacksonXmlElementWrapper
    private List<GeneralField> listmania = new ArrayList<>();

    private Musicspec musicspec;
    @JacksonXmlElementWrapper
    private List<GeneralField> tracks = new ArrayList<>();
    @JacksonXmlElementWrapper
    private List<GeneralField> labels = new ArrayList<>();
    @JacksonXmlElementWrapper
    private List<GeneralField> artists = new ArrayList<>();

    private DVDspec dvdspec;
    @JacksonXmlElementWrapper
    private List<GeneralField> studios = new ArrayList<>();
    @JacksonXmlElementWrapper
    private List<GeneralField> creators = new ArrayList<>();
    @JacksonXmlElementWrapper
    private List<GeneralField> actors = new ArrayList<>();
    @JacksonXmlElementWrapper
    private List<GeneralField> directors = new ArrayList<>();

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Audiotext> audiotext;
    private String picture;
    private String detailpage;
    private String state;

    public Item(String asin, String ean, String pgroup, String title, String salesrank, Price price, Detail details, List<Item> similars, String value, Bookspec bookspec, List<GeneralField> authors, List<GeneralField> publishers, List<GeneralField> listmania, Musicspec musicspec, List<GeneralField> tracks, List<GeneralField> labels, List<GeneralField> artists, DVDspec dvdspec, List<GeneralField> studios, List<GeneralField> creators, List<GeneralField> actors, List<GeneralField> directors, List<Audiotext> audiotext, String picture, String detailpage, String state) {
        this.asin = asin;
        this.ean = ean;
        this.pgroup = pgroup;
        this.title = title;
        this.salesrank = salesrank;
        this.price = price;
        this.details = details;
        this.similars = similars;
        this.value = value;
        this.bookspec = bookspec;
        this.authors = authors;
        this.publishers = publishers;
        this.listmania = listmania;
        this.musicspec = musicspec;
        this.tracks = tracks;
        this.labels = labels;
        this.artists = artists;
        this.dvdspec = dvdspec;
        this.studios = studios;
        this.creators = creators;
        this.actors = actors;
        this.directors = directors;
        this.audiotext = audiotext;
        this.picture = picture;
        this.detailpage = detailpage;
        this.state = state;
    }

    public Item() {

    }
}
