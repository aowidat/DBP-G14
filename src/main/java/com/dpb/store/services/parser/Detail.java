package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Detail {
    @JacksonXmlProperty(isAttribute = true)
    private String img;
    @JacksonXmlText
    private String value;

    public Detail() {

    }
}
