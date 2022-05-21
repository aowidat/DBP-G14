package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Bookspec {
    private String binding;
    private Edition edition;

    private ISBN isbn;

    @JacksonXmlProperty(localName = "package")
    private Package bookPackage;

    private String pages;

    private Date publication;

    public Bookspec() {

    }

    public String getTheRealISBN() {
        return this.isbn.getVal();
    }

    public String getTheRealPublication() {
        return this.publication.getDate();
    }

    public String getTheRealEdition() {
        return this.edition.getVal();
    }
}
