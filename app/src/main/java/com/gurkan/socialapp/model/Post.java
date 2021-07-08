package com.gurkan.socialapp.model;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {

    private String content;
    private String date;
    private String location;
    private String title;
    private List<String> listContentImage;
    private Integer viewType;

    public Post(String content, String date, String location, String title, List<String> listContentImage, Integer viewType) {
        this.content = content;
        this.date = date;
        this.location = location;
        this.title = title;
        this.listContentImage = listContentImage;
        this.viewType = viewType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getListContentImage() {
        return listContentImage;
    }

    public void setListContentImage(List<String> listContentImage) {
        this.listContentImage = listContentImage;
    }

    public Integer getViewType() {
        return viewType;
    }

    public void setViewType(Integer viewType) {
        this.viewType = viewType;
    }
}
