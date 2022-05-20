package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Categories {
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CategoryBean> category;

    public Categories() {

    }
}
