package com.dpb.store.services.parser;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;


public class Item {
    @JacksonXmlProperty(isAttribute = true)
    private String pgroup;
    @JacksonXmlProperty(isAttribute = true)
    private String asin;
    @JacksonXmlProperty(isAttribute = true)
    private int salesrank;

    private String title;
    private Price price;
    private String ean;
    private Detail details;

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
    private List<Item> similars;

    @JacksonXmlElementWrapper(localName = "tracks")
    @JacksonXmlProperty(localName = "title")
    private List<String> tracks;


    //@JacksonXmlElementWrapper
    // private List<Audiotext> audiotext;

    private DVDspec dvdspec;

    private Bookspec bookspec;

    private Musicspec musicspec;

}
