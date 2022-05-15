package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Item {
    @JacksonXmlProperty(isAttribute = true)
    private String pgroup;
    @JacksonXmlProperty(isAttribute = true)
    private String asin;
    @JacksonXmlProperty(isAttribute = true)
    private int salesrank;
    @JacksonXmlProperty(localName = "title")
    private String title;

    private Price price;
    private String ean;
    private Detail details;
    private String picture;
    private String detailpage;


    @JacksonXmlElementWrapper(localName = "listmania")
    @JacksonXmlProperty(localName = "list")
    private List<String> list;

    @JacksonXmlElementWrapper(localName = "actors")
    @JacksonXmlProperty(localName = "actor")
    private List<String> actor;

    @JacksonXmlElementWrapper(localName = "artists")
    @JacksonXmlProperty(localName = "artist")
    private List<String> artist;

    @JacksonXmlElementWrapper(localName = "authors")
    @JacksonXmlProperty(localName = "author")
    private List<String> author;

    @JacksonXmlElementWrapper(localName = "creators")
    @JacksonXmlProperty(localName = "creator")
    private List<String> creator;

    @JacksonXmlElementWrapper(localName = "directors")
    @JacksonXmlProperty(localName = "director")
    private List<String> director;

    @JacksonXmlElementWrapper(localName = "labels")
    @JacksonXmlProperty(localName = "label")
    private List<String> label;

    @JacksonXmlElementWrapper(localName = "publishers")
    @JacksonXmlProperty(localName = "publisher")
    private List<String> publisher;

    @JacksonXmlElementWrapper(localName = "studios")
    @JacksonXmlProperty(localName = "studio")
    private List<String> studio;

    @JacksonXmlElementWrapper(localName = "similars")
    @JacksonXmlProperty(localName = "item")
    private List<Item> similar;

    @JacksonXmlElementWrapper(localName = "tracks")
    private List<String> tracks;
    private String mult;
    private String state;


    private String currency;


    //@JacksonXmlElementWrapper(localName = "audiotext", useWrapping = false)
    //private List<Audiotext> audiotext;
    // private List<Audiotext> audiotext;
    //private List<String> language;
    //private List<String> audioformat;

    private DVDspec dvdspec;

    private Bookspec bookspec;

    private Musicspec musicspec;

    public Item(String pgroup, String asin, int salesrank, String title, Price price, String ean, Detail details, String picture, String detailpage, List<String> list, List<String> actor, List<String> artist, List<String> author, List<String> creator, List<String> director, List<String> label, List<String> publisher, List<String> studio, List<Item> similars, List<String> tracks, List<Audiotext> audiotext, String mult, String state, String currency, DVDspec dvdspec, Bookspec bookspec, Musicspec musicspec) {
        this.pgroup = pgroup;
        this.asin = asin;
        this.salesrank = salesrank;
        this.title = title;
        this.price = price;
        this.ean = ean;
        this.details = details;
        this.picture = picture;
        this.detailpage = detailpage;
        this.list = list;
        this.actor = actor;
        this.artist = artist;
        this.author = author;
        this.creator = creator;
        this.director = director;
        this.label = label;
        this.publisher = publisher;
        this.studio = studio;
        this.similar = similars;
        this.tracks = tracks;
        this.mult = mult;
        this.state = state;
        this.currency = currency;
        this.dvdspec = dvdspec;
        this.bookspec = bookspec;
        this.musicspec = musicspec;
    }

    public Item() {

    }
}
