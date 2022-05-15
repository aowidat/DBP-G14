package com.dpb.store.services.parser;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Audiotext {
    private List<AudioLanguage> audioLanguage;
    private String audioformat;


    public Audiotext(List<AudioLanguage> audioLanguage, String audioformat) {
        this.audioformat = audioformat;
        this.audioLanguage = audioLanguage;
    }

    public Audiotext() {
    }
}
