package com.dpb.store.services.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * a custom category deserializer, because category has a recursive structure and not a united form
 */
public class CustomCategoryDeserializer extends JsonDeserializer<CategoryBean> {
    @Override
    public CategoryBean deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        //trying to deserialize one Category
        CategoryBean categoryBean = new CategoryBean();
        try {
            //convert it to standard category
            StandardCategory standardCategory = jp.readValueAs(StandardCategory.class);
            String categoryName = standardCategory.categoryName;
            try {
                categoryName = standardCategory.categoryName.replaceAll("[\\n\\t ]", "");
            } catch (NullPointerException ignored) {
                    //nothing to do
            } finally {
                categoryBean.setCategoryName(categoryName);
            }
            categoryBean.setCategory(standardCategory.category);
            categoryBean.setItem(standardCategory.item);
        } catch (JsonMappingException me) {
            // Category has an abnormal structure
            EmptyCategory emptyCategory = jp.readValueAs(EmptyCategory.class);
            String categoryName = emptyCategory.categoryName;
            try {
                categoryName = emptyCategory.categoryName.replaceAll("[\\n\\t ]", "");
            } catch (NullPointerException ignored) {
                //nothing to do
            } finally {
                categoryBean.setCategoryName(categoryName);
            }
            ArrayList<CategoryBean> newList = new ArrayList<>();
            CategoryBean cb = new CategoryBean();
            cb.setCategoryName(emptyCategory.category);
            newList.add(cb);
            categoryBean.setCategory(newList);
        }
        return categoryBean;
    }

    /**
     * a help class which represent a normal category
     */
    private static class StandardCategory {
        @JacksonXmlText
        protected String categoryName;
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<CategoryBean> category = new ArrayList<>();
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<String> item;
    }

    /**
     * a help class which represent an empty category
     */
    private static class EmptyCategory {
        @JacksonXmlText
        protected String categoryName;
        String category;
    }
}
