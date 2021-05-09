package com.example.cwversion3.database.entities;

public class Sound {
    private int id;
    private String sound_path;

    public Sound(int id, String sound_path) {
        this.id = id;
        this.sound_path = sound_path;
    }

    public int getId() {
        return id;
    }

    public String getSound_path() {
        return sound_path;
    }

    @Override
    public String toString() {
        return "Sound{" +
                "id=" + id +
                ", sound_path='" + sound_path + '\'' +
                '}';
    }
}
