package com.dpb.store.services.parser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * a class used by Jackson to deserialize a tag called categories, which has a list of category
 */
@Getter
@Setter
@NoArgsConstructor
public class Categories {
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CategoryBean> category;
}
