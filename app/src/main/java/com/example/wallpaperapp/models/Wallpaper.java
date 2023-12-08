package com.example.wallpaperapp.models;

public class Wallpaper {
    private String medium,large;

    public Wallpaper(String medium, String large) {
        this.medium = medium;
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
