package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Bookspec {
    private String binding;
    private String edition;
    private String isbn;

    @JacksonXmlProperty(localName = "package")
    private Package bookPackage;

    private String pages;
    private String publication;


}
