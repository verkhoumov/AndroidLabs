package ru.verkhoumov.navigation;

public class System {
    private String name;
    private int imageResourceId;

    public static final System[] systems = {
            new System("Android", R.drawable.android),
            new System("iPhone", R.drawable.iphone),
            new System("Windows Phone", R.drawable.windows),
            new System("BlackBerry", R.drawable.black)
    };

    private System(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
