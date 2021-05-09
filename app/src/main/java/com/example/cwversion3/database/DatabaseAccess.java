package com.example.cwversion3.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cwversion3.database.entities.Image;
import com.example.cwversion3.database.entities.Lesson;
import com.example.cwversion3.database.entities.Sound;
import com.example.cwversion3.database.entities.Theme;
import com.example.cwversion3.database.entities.Word;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    //constructor
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //return the single instance of database
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    //to open the database
    public void open() {
        this.db = openHelper.getReadableDatabase();
    }

    //closing the database connection
    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    //methods to query

    public String getPath(int id) {
        c = db.rawQuery("select image_path from images where id = '" + id + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String imagePath = c.getString(0);
            buffer.append("" + imagePath);
        }
        return buffer.toString();
    }

    public List<Theme> getAllThemes() {
        List<Theme> themes = new ArrayList<Theme>();
        c = db.query("themes", null, null, null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Theme t = new Theme(Integer.parseInt(c.getString(c.getColumnIndex("id"))),
                            c.getString(c.getColumnIndex("name")),
                            Integer.parseInt(c.getString(c.getColumnIndex("image_id"))) );
                    themes.add(t);
                } while (c.moveToNext());
            }
        } else
            Log.d("myLogs", "Cursor is null");
        return themes;
    }

    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<Lesson>();
        c = db.query("lessons", null, null, null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Lesson l = new Lesson(Integer.parseInt(c.getString(c.getColumnIndex("id"))),
                            c.getString(c.getColumnIndex("name")),
                            Integer.parseInt(c.getString(c.getColumnIndex("theme_id"))),
                            Integer.parseInt(c.getString(c.getColumnIndex("image_id"))));
                    lessons.add(l);
                } while (c.moveToNext());
            }
        } else
            Log.d("myLogs", "Cursor is null");
        return lessons;
    }

    public List<Word> getAllWords() {
        List<Word> words = new ArrayList<Word>();
        c = db.query("words", null, null, null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Word w = new Word(Integer.parseInt(c.getString(c.getColumnIndex("id"))),
                            c.getString(c.getColumnIndex("word_ru")),
                            c.getString(c.getColumnIndex("word_by")),
                            Integer.parseInt(c.getString(c.getColumnIndex("lesson_id"))),
                            Integer.parseInt(c.getString(c.getColumnIndex("image_id"))),
                            Integer.parseInt(c.getString(c.getColumnIndex("sound_id"))));
                    words.add(w);
                } while (c.moveToNext());
            }
        } else
            Log.d("myLogs", "Cursor is null");
        return words;
    }

    public List<Image> getAllImages() {
        List<Image> images = new ArrayList<Image>();
        c = db.query("images", null, null, null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Image i = new Image(Integer.parseInt(c.getString(c.getColumnIndex("id"))),
                            c.getString(c.getColumnIndex("image_path")));
                    images.add(i);
                } while (c.moveToNext());
            }
        } else
            Log.d("myLogs", "Cursor is null");
        return images;
    }

    public List<Sound> getAllSounds() {
        List<Sound> sounds = new ArrayList<Sound>();
        c = db.query("sounds", null, null, null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Sound s = new Sound(Integer.parseInt(c.getString(c.getColumnIndex("id"))),
                            c.getString(c.getColumnIndex("sound_path")));
                    sounds.add(s);
                } while (c.moveToNext());
            }
        } else
            Log.d("myLogs", "Cursor is null");
        return sounds;
    }
/*
    public List<Lesson> getLessonsOfTheTheme(Theme theme) {
        List<Lesson> lessons = new ArrayList<Lesson>();
        c = db.query("lessons", null, "theme_id = " + theme.getId(), null, null, null, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Lesson l = new Lesson(Integer.parseInt(c.getString(c.getColumnIndex("id"))),
                            c.getString(c.getColumnIndex("name")),
                            Integer.parseInt(c.getString(c.getColumnIndex("theme_id"))),
                            Integer.parseInt(c.getString(c.getColumnIndex("image_id"))));
                    lessons.add(l);
                } while (c.moveToNext());
            }
        } else
            Log.d("myLogs", "Cursor is null");
        return lessons;
    }

*/

    void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d("myLogs", str);
                } while (c.moveToNext());
            }
        } else
            Log.d("myLogs", "Cursor is null&&&&&&&&&&&&&&&&&&&&&");
    }
}
