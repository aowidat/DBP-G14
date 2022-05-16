package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Musicspec {
    private String binding;
    private Format format;
    private String num_discs;
    private String releasedate;
    private String upc;


    public Musicspec() {

    }
}
