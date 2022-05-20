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

public class CustomCategoryDeserializer extends JsonDeserializer<CategoryBean> {

    /**
     * Erzeugt eine CategoryBean aus sowohl wohlgeformten als auch nicht typischen Kategorien.
     */
    @Override
    public CategoryBean deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        CategoryBean categoryBean = new CategoryBean();

        try {
            StandardCategory standardCategory = jp.readValueAs(StandardCategory.class);
            String categoryName = standardCategory.categoryName;
            try {
                categoryName = standardCategory.categoryName.replaceAll("[\\n\\t ]", "");
            } catch (NullPointerException ignored) {

            } finally {
                categoryBean.setCategoryName(categoryName);
            }
            categoryBean.setCategory(standardCategory.category);
            categoryBean.setItem(standardCategory.item);

        } catch (JsonMappingException me) {
            // Category is empty, XML has weird format, apply custom Bean
            EmptyCategory emptyCategory = jp.readValueAs(EmptyCategory.class);
            String categoryName = emptyCategory.categoryName;
            try {
                categoryName = emptyCategory.categoryName.replaceAll("[\\n\\t ]", "");
            } catch (NullPointerException ignored) {

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

    private static class StandardCategory {

        @JacksonXmlText
        protected String categoryName;

        @JacksonXmlElementWrapper(useWrapping = false)
        private List<CategoryBean> category = new ArrayList<>();

        @JacksonXmlElementWrapper(useWrapping = false)
        private List<String> item;
    }

    private static class EmptyCategory {

        @JacksonXmlText
        protected String categoryName;

        String category;
    }
}
