package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a class used by Jackson to deserialize a tag called bookspec
 */
@Setter
@Getter
@NoArgsConstructor
public class Bookspec {
    private String binding;
    private Edition edition;
    private ISBN isbn;
    @JacksonXmlProperty(localName = "package")
    private Package bookPackage;
    private String pages;
    private Date publication;

    /**
     * function to get the value of isbn
     * @return isbn
     */
    public String getTheRealISBN() {
        return this.isbn.getVal();
    }

    /**
     * function to get the date of the publication
     * @return publications date
     */
    public String getTheRealPublication() {
        return this.publication.getDate();
    }

    /**
     * a function to get the value of the edition
     * @return edition
     */
    public String getTheRealEdition() {
        return this.edition.getVal();
    }
}
