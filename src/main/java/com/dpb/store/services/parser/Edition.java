package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a class used by Jackson to deserialize a tag called edition
 */
@Getter
@Setter
@NoArgsConstructor
public class Edition {
    @JacksonXmlProperty(isAttribute = true)
    private String val;
}
