package com.dpb.store.services.parser;


import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Audiotext {
    private List<AudioLanguage> language;
    private List<String> audioformat;

    public Audiotext() {
    }

    @JsonSetter(value = "language")
    public void setLanguageFromXML(AudioLanguage language) {
        if (this.language == null) {
            this.language = new ArrayList<>();
        }
        this.language.add(language);
    }

    @JsonSetter(value = "audioformat")
    public void setAudioFormatFromXML(String audioformat) {
        if (this.audioformat == null) {
            this.audioformat = new ArrayList<>();
        }
        this.audioformat.add(audioformat);
    }
}
