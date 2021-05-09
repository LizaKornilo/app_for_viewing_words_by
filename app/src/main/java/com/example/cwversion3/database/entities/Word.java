package com.example.cwversion3.database.entities;

public class Word {
    private int id;
    private String word_ru;
    private String word_by;
    private int lesson_id;
    private int image_id;
    private int sound_id;

    public Word(int id, String word_ru, String word_by, int lesson_id, int image_id, int sound_id) {
        this.id = id;
        this.word_ru = word_ru;
        this.word_by = word_by;
        this.lesson_id = lesson_id;
        this.image_id = image_id;
        this.sound_id = sound_id;
    }

    public int getId() {
        return id;
    }

    public String getWord_ru() {
        return word_ru;
    }

    public String getWord_by() {
        return word_by;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public int getImage_id() {
        return image_id;
    }

    public int getSound_id() {
        return sound_id;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word_ru='" + word_ru + '\'' +
                ", word_by='" + word_by + '\'' +
                ", lesson_id=" + lesson_id +
                ", image_id=" + image_id +
                ", sound_id=" + sound_id +
                '}';
    }
}
