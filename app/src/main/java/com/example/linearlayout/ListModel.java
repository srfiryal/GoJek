package com.example.linearlayout;

public class ListModel {
    int imageId;
    String title, desc;

    public ListModel(int image, String title, String desc) {
        this.imageId = image;
        this.title = title;
        this.desc = desc;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
