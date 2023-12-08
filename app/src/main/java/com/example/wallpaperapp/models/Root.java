package com.example.wallpaperapp.models;

import java.util.List;

public class Root {
    private List<Photos> photos;

    public Root(List<Photos> photos) {
        this.photos = photos;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }
}
