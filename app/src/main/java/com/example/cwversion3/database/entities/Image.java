package com.example.cwversion3.database.entities;

public class Image {
    private int id;
    private String image_path;

    public Image(int id, String image_path) {
        this.id = id;
        this.image_path = image_path;
    }

    public int getId() {
        return id;
    }

    public String getImage_path() {
        return image_path;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", image_path='" + image_path + '\'' +
                '}';
    }
}
