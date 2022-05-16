package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Price {
    @JacksonXmlProperty(isAttribute = true)
    private double mult;
    @JacksonXmlProperty(isAttribute = true)
    private String state;
    @JacksonXmlProperty(isAttribute = true)
    private String currency;
    @JacksonXmlText
    private double price;
    @JacksonXmlProperty(isAttribute = true)
    private String title;

}
