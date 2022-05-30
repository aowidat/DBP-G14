package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a class used by Jackson to deserialize a tag called Format
 */
@Getter
@Setter
@NoArgsConstructor
public class Format {
    @JacksonXmlProperty(isAttribute = true)
    private String value;
    @JacksonXmlText
    private String name;
    /**
     * a function to get the data from the tag, aside from how it's saved
     * @return the date
     */
    public String getTheRealValue() {
        if (name != null) {
            return name;
        } else if (value != null) {
            return value;
        } else return null;
    }
}
