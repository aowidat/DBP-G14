package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AudioLanguage {
    @JacksonXmlProperty(isAttribute = true)
    private String type;
    @JacksonXmlText
    private String language;

    public AudioLanguage() {

    }
    public AudioLanguage(String type , String language){
        this.type = type;
        this.language = language;
    }

}
