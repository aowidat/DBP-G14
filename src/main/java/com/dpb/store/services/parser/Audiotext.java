package com.dpb.store.services.parser;


import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *  a class used by Jackson to deserialize a tag audiotext
 */
@Getter
@NoArgsConstructor
public class Audiotext {
    private List<AudioLanguage> language;
    private List<String> audioformat;

    /**
     * Custom Set-Method for Jackson to add a Audio-Language
     * @param language language to be added as an Audio-Language
     */
    @JsonSetter(value = "language")
    public void setLanguageFromXML(AudioLanguage language) {
        if (this.language == null) {
            this.language = new ArrayList<>();
        }
        this.language.add(language);
    }

    /**
     * Custom Set-Method for Jackson to add a Audio-Format
     * @param audioformat Format to be added as an Audio-Format
     */
    @JsonSetter(value = "audioformat")
    public void setAudioFormatFromXML(String audioformat) {
        if (this.audioformat == null) {
            this.audioformat = new ArrayList<>();
        }
        this.audioformat.add(audioformat);
    }
}
