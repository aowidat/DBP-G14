package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A Class used by Jackson to deserialize a Tag named item, which has some attributes and under tags
 */
@Setter
@Getter
@NoArgsConstructor
public class Item {
    private String asin;
    private String ean;
    private String pgroup;
    private String title;
    private String salesrank;
    @JacksonXmlElementWrapper(useWrapping = false)
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
    /**
     * title of the similar item, when it's saved nested
     */
    @JacksonXmlText
    private String titleOfSimilar;

    /**
     * function to get the source to the image of the item, when it's saved in under tags
     *
     * @return the source of the Image of the Item
     */
    public String getTheRealImg() {
        if (picture == null && details.getImg() == null) {
            return null;
        } else if (picture != null) {
            return picture;
        } else return this.details.getImg();
    }

    /**
     * function to get the title of the item, doesn't matter if it's an item or a similar item.
     *
     * @return the title of the item
     */
    public String getTheRealTitle() {
        if (this.title != null) {
            return title;
        } else if (titleOfSimilar != null) {
            return titleOfSimilar;
        } else return null;
    }

    /**
     * ToString Method to print an Item
     * @return Item as a String
     */
    @Override
    public String toString() {
        return "Item{" +
                "asin='" + asin + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
