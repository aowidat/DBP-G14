package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a class used by Jackson to deserialize a tag called audioLanguage
 */
@Setter
@Getter
@NoArgsConstructor
public class AudioLanguage {
    @JacksonXmlProperty(isAttribute = true)
    private String type;
    @JacksonXmlText
    private String language;

}
