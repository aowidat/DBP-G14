package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Format {
    @JacksonXmlProperty(isAttribute = true)
    private String value;
    @JacksonXmlText
    private String name;

    public Format() {

    }
    public String getTheRealValue() {
        if (name != null) {
            return name;
        } else if (value != null) {
            return value;
        } else return null;
    }
}
