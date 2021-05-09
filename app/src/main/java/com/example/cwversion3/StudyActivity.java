package com.example.cwversion3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.cwversion3.database.entities.Lesson;
import com.example.cwversion3.database.entities.Theme;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyActivity extends AppCompatActivity {

    ExpandableListView exListView;
    List<Theme> themes;
    HashMap<Theme, List<Lesson>>  themes_lessons;
    ThemesAndLessonsAdapter adapter;
    //TextView t; //(удалить из xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        exListView = findViewById(R.id.exListView);
        themes = App.getInstance().getThemes();
        themes_lessons = App.getInstance().getThemesLessons();

        adapter = new ThemesAndLessonsAdapter(this, themes, themes_lessons);
        adapter.notifyDataSetChanged();
        exListView.setAdapter(adapter);

      /*  t = findViewById(R.id.t);
        int i=0;
        String msg = "";
        for(Map.Entry<Theme, List<Lesson>> entry : themes_lessons.entrySet()) {
            msg +=i + " " + entry.getKey().toString() + " " + entry.getValue().toString();

            ++i; //iterate
        }

        t.setText(msg);*/


    }
}