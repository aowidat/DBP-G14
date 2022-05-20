package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Price {
    @JacksonXmlProperty(isAttribute = true)
    private String mult;
    @JacksonXmlProperty(isAttribute = true)
    private String state;
    @JacksonXmlProperty(isAttribute = true)
    private String currency;
    @JacksonXmlText
    private String price;
    @JacksonXmlProperty(isAttribute = true)
    private String title;

    public Price() {
    }

}
