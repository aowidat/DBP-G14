package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Date {
    @JacksonXmlProperty(isAttribute = true)
    private String date;

    public Date() {

    }
}
