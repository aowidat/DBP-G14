package com.dpb.store.services.parser;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@JsonDeserialize(using = CustomCategoryDeserializer.class)
public class CategoryBean {

    private String categoryName;

    private List<String> item;

    private List<CategoryBean> category;


    public String toString() {
        return "CategoryBean(categoryName=" + this.getCategoryName() + ", item=" + this.getItem() + ", category=" + this.getCategory() + ")";
    }
}
