package com.dpb.store.services.parser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;


public class Shop {
    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @JacksonXmlProperty(isAttribute = true)
    private String street;
    @JacksonXmlProperty(isAttribute = true)
    private String zip;
    @JsonProperty("item")
    private List<Item> items;

    public Shop(String name, String street, String zip, List<Item> items) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.items = items;

    }

    public Shop() {

    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }


    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }


    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<Item> getItems() {
        return items;
    }


    public void setItems(List<Item> items) {
        this.items = items;
    }


}
