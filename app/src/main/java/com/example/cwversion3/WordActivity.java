package com.example.cwversion3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cwversion3.database.entities.Lesson;
import com.example.cwversion3.database.entities.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.LoggingMXBean;

public class WordActivity extends AppCompatActivity {

    ViewPager pager;
    PagerAdapter pagerAdapter;
    int lesson_id;
    List<Word> words;
    private ProgressBar pbHorizontal;
    private float progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        pbHorizontal = (ProgressBar) findViewById(R.id.pb_horizontal);

        Bundle arguments = getIntent().getExtras();
        lesson_id = Integer.parseInt(arguments.get("lesson_id").toString());
        words = App.getInstance().findWordsByLessonId(lesson_id);


        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                progress = ((position+1)*100)/words.size();
                pbHorizontal.setProgress((int) progress);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }



    private void postProgress(int progress) {
        String strProgress = String.valueOf(progress) + " %";
        pbHorizontal.setProgress(progress);
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return WordFragment.newInstance(App.getInstance().findImagePathById(words.get(position).getImage_id()),
                    words.get(position).getWord_by(),
                    App.getInstance().findSoundPathById(words.get(position).getSound_id()));
        }

        @Override
        public int getCount() {
            return words.size();
        }

    }
}