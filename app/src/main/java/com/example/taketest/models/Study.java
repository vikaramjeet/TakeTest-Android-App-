package com.example.taketest.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Study implements Serializable {

    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
