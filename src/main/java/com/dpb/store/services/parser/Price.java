package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A class used by Jackson to represent a tag named price, which has mult, state, currency and title
 */
@Setter
@Getter
@NoArgsConstructor
public class Price {
    @JacksonXmlProperty(isAttribute = true)
    private String mult;
    @JacksonXmlProperty(isAttribute = true)
    private String state;
    @JacksonXmlProperty(isAttribute = true)
    private String currency;
    @JacksonXmlText
    private String price;

}
