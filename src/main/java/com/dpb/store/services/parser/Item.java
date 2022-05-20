package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
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
    @JacksonXmlText
    private String titleOfSimilar;

    public String getTheRealImg() {
        if (detailpage == null && details.getImg() == null) {
            return null;
        } else if (detailpage != null) {
            return detailpage;
        } else return this.details.getImg();
    }

    public String getTheRealTitle() {
        if (this.title != null) {
            return title;
        } else if (titleOfSimilar != null) {
            return titleOfSimilar;
        } else return null;
    }

    public Item() {

    }

    @Override
    public String toString() {
        return "Item{" +
                "asin='" + asin + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
