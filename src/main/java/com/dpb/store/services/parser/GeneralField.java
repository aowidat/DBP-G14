package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralField {
    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @JacksonXmlText
    private String value;

    public GeneralField() {
    }

    public String getTheRealValue() {
        if (name != null) {
            return name;
        } else if (value != null) {
            return value;
        } else return "NULL";
    }
}
