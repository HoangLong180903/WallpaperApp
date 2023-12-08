package com.example.wallpaperapp.models;

public class Photos {
    private Wallpaper src;

    public Photos(Wallpaper src) {
        this.src = src;
    }

    public Wallpaper getSrc() {
        return src;
    }

    public void setSrc(Wallpaper src) {
        this.src = src;
    }
}
