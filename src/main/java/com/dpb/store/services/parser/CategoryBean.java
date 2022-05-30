package com.dpb.store.services.parser;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * a class used by Jackson to deserialize a tag called category, which has name, list of item and list of category.
 * to deserialize the tag a custom deserializer is required
 */
@Getter
@Setter
@JsonDeserialize(using = CustomCategoryDeserializer.class)
@NoArgsConstructor
public class CategoryBean {

    private String categoryName;

    private List<String> item;

    private List<CategoryBean> category;


    public String toString() {
        return "CategoryBean(categoryName=" + this.getCategoryName() + ", item=" + this.getItem() + ", category=" + this.getCategory() + ")";
    }
}
