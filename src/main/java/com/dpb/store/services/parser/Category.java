package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import javax.xml.bind.annotation.*;

import com.sun.xml.bind.annotation.XmlLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@Getter
@Setter
public class Category {
    @XmlElement(name = "category")
    private List<Category> categories = new ArrayList<>();
    @XmlElement(name = "item")
    private List<String> item = new ArrayList<>();
    @JacksonXmlText
    private String category;

    public Category() {
    }
}
