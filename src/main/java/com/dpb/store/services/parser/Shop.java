package com.dpb.store.services.parser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Data

@JacksonXmlRootElement (localName = "shop")
public class Shop {
//    @JacksonXmlProperty(isAttribute = true)
    private String name;
//    @JacksonXmlProperty(isAttribute = true)
    private String street;
//    @JacksonXmlProperty(isAttribute = true)
    private String zip;

    public Shop(String name, String street, String zip, List<Item> item) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.item = item;
    }

    public Shop(){

    }

    @JacksonXmlElementWrapper(localName = "shop", useWrapping = false)
    @JacksonXmlProperty(localName = "item")
    //@JsonIgnore
    private List<Item> item;
}
