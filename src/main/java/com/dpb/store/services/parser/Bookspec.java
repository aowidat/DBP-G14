package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Bookspec {
    private String binding;
    private String edition;
    private String isbn;

    @JacksonXmlProperty(localName = "package")
    private Package bookPackage;

    private String pages;
    private String publication;

    public Bookspec(String binding, String edition, String isbn, Package bookPackage, String pages, String publication) {
        this.binding = binding;
        this.edition = edition;
        this.isbn = isbn;
        this.bookPackage = bookPackage;
        this.pages = pages;
        this.publication = publication;
    }
    public Bookspec(){

    }
}
