package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Bookspec {
    private String binding;
    private String edition;

    private ISBN isbn;

    @JacksonXmlProperty(localName = "package")
    private Package bookPackage;

    private String pages;

    private String publication;

    public Bookspec() {

    }

    public String getTheRealISBN() {
        return this.isbn.getVal();
    }
}
