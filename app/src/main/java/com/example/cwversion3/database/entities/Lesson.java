package com.example.cwversion3.database.entities;

public class Lesson {
    private int id;
    private String name;
    private int theme_id;
    private int image_id;

    public Lesson(int id, String name, int theme_id, int image_id) {
        this.id = id;
        this.name = name;
        this.theme_id = theme_id;
        this.image_id = image_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTheme_id() {
        return theme_id;
    }

    public int getImage_id() {
        return image_id;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", theme_id=" + theme_id +
                ", image_id=" + image_id +
                '}';
    }
}
