package com.dpb.store.services.parser;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(using = CustomCategoryDeserializer.class)
public class CategoryBean {

    private String categoryName;

    private List<String> item;

    private List<CategoryBean> category;

    public CategoryBean() {
        this.category = new ArrayList<>();
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public List<String> getItem() {
        return this.item;
    }

    public List<CategoryBean> getCategory() {
        return this.category;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CategoryBean)) return false;
        final CategoryBean other = (CategoryBean) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$categoryName = this.getCategoryName();
        final Object other$categoryName = other.getCategoryName();
        if (!Objects.equals(this$categoryName, other$categoryName))
            return false;
        final Object this$item = this.getItem();
        final Object other$item = other.getItem();
        if (!Objects.equals(this$item, other$item)) return false;
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        if (!Objects.equals(this$category, other$category)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CategoryBean;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $categoryName = this.getCategoryName();
        result = result * PRIME + ($categoryName == null ? 43 : $categoryName.hashCode());
        final Object $item = this.getItem();
        result = result * PRIME + ($item == null ? 43 : $item.hashCode());
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        return result;
    }

    public String toString() {
        return "CategoryBean(categoryName=" + this.getCategoryName() + ", item=" + this.getItem() + ", category=" + this.getCategory() + ")";
    }
}
