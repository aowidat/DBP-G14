package com.dpb.store.services.parser;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "shop")
public class Shop {
    private String name;
    private String street;
    private String zip;
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Item> item;


    public Shop(String name, String street, String zip, List<Item> item) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.item = item;
    }

    public Shop() {

    }
}
