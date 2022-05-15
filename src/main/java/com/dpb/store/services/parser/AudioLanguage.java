package com.dpb.store.services.parser;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AudioLanguage {
    private String type;
    private String language;

    public AudioLanguage() {

    }
    public AudioLanguage(String type , String language){
        this.type = type;
        this.language = language;
    }

}
