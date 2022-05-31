package com.dpb.store.services.parser;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A Class used by Jackson to deserialize a Tag named Shop, with parameters name, street, zip and list of items
 */
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "shop")
public class Shop {
    private String name;
    private String street;
    private String zip;
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Item> item;
    /**
     * ToString Method to print an Shop
     * @return Shop as a String
     */
    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
