package com.mastercoding.mp5;

public class AddItem {
    private int imageResource;
    private final String title;

    AddItem(String title) {
        this.title = title;
    }

    AddItem(String title, int imageId) {
        this.title = title;
        this.imageResource = imageId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageId() {
        return imageResource;
    }
}
