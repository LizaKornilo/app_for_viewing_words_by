package com.example.cwversion3;

import android.app.Application;
import android.util.Log;

import com.example.cwversion3.database.DatabaseAccess;
import com.example.cwversion3.database.entities.Image;
import com.example.cwversion3.database.entities.Lesson;
import com.example.cwversion3.database.entities.Sound;
import com.example.cwversion3.database.entities.Theme;
import com.example.cwversion3.database.entities.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App extends Application {
    public static App instance;
    List<Theme> themes;
    List<Lesson> lessons;
    List<Word> words;
    List<Image> images;
    List<Sound> sounds;
    HashMap<Theme, List<Lesson>> themes_lessons = new HashMap<>();
    HashMap<Lesson, List<Word>> lessons_words = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        themes = databaseAccess.getAllThemes();
        lessons = databaseAccess.getAllLessons();
        words = databaseAccess.getAllWords();
        images = databaseAccess.getAllImages();
        sounds = databaseAccess.getAllSounds();

        for(Theme t: themes){
            List<Lesson> lessonsList = new ArrayList<>();
            for(Lesson l: lessons){
                if (l.getTheme_id() == t.getId())
                    lessonsList.add(l);
            }
            themes_lessons.put(t, lessonsList);
        }


        databaseAccess.close();
    }

    public static App getInstance() {
        return instance;
    }

    public List<Theme> getThemes() {
            return themes;
    }
    public List<Lesson> getLessons() {
        return lessons;
    }
    public List<Word> getWords() {
        return words;
    }
    public List<Image> getImages() {
        return images;
    }
    public List<Sound> getSounds() {
        return sounds;
    }
    public  HashMap<Theme, List<Lesson>> getThemesLessons() {
        return themes_lessons;
    }

    public List<Word> findWordsByLessonId(int lesson_id){
        List<Word> words = new ArrayList<Word>();
        for (Word w: this.words){
            if(w.getLesson_id() == lesson_id)
                words.add(w);
        }
        return words;
    }

    public String findImagePathById(int image_id){
        String image_path = "cw_img_sound/no-image.png";
        for (Image i: images){
            if(i.getId() == image_id)
                image_path = i.getImage_path();
        }
        return image_path;
    }

    public String findSoundPathById(int sound_id){
        String suund_path = null;
        for (Sound s: sounds){
            if(s.getId() == sound_id)
                suund_path = s.getSound_path();
        }
        return suund_path;
    }

}
