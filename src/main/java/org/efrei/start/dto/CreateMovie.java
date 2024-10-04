package org.efrei.start.dto;

import java.util.Locale.Category;

public class CreateMovie {


    private String title;
    
    private Category category;


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
