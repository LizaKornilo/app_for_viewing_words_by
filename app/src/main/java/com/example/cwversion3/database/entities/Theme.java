package com.example.cwversion3.database.entities;

public class Theme {
    private int id;
    private String name;
    private int image_id;

    public Theme(int id, String name, int image_id) {
        this.id = id;
        this.name = name;
        this.image_id = image_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImage_id() {
        return image_id;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_id=" + image_id +
                '}';
    }
}
