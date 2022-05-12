package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class Detail {
    @JacksonXmlProperty(isAttribute = true)
    private String img;
    @JacksonXmlText
    private String value;

    public Detail(String img, String value) {
        this.img = img;
        this.value = value;
    }

    public Detail() {

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
