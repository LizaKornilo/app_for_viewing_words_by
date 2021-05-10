package com.example.cwversion3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.cwversion3.database.entities.Word;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ImageButton ibSound;
    List<Word> words;
    Word[] otherWords;
    List<ImageButton> imageButtons = new ArrayList<ImageButton>();
    Integer answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imageButtons.add((ImageButton) findViewById(R.id.iv1));
        imageButtons.add((ImageButton) findViewById(R.id.iv2));
        imageButtons.add((ImageButton) findViewById(R.id.iv3));
        imageButtons.add((ImageButton) findViewById(R.id.iv4));
        ibSound = findViewById(R.id.ivSoundBtn);
        words = App.getInstance().getWords();

        generateRandomData();
        setData();

        ibSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(App.getInstance().findSoundPathById(otherWords[answer].getSound_id()));
            }
        });

        setClickListerners();
    }

    public void generateRandomData() {
        otherWords  = new Word[4];
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Word w : words) {
            ids.add(new Integer(w.getId()));
        }
        Collections.shuffle(ids);

        //set random mainWord and otherWords(3 items)
        for (int i = 0; i < otherWords.length; i++) {
            otherWords[i] = words.get(ids.get(i));
        }
        //answer = (int) (Math.random() * 4);//random int num in range [0, 3]
        Random randnum = new Random();
        answer = randnum.nextInt(4);
        Log.d("my", String.valueOf(answer));
    }

    public void setData() {
        for (int i = 0; i < 4; i++) {
            displayImage(App.getInstance().findImagePathById(otherWords[i].getImage_id()), imageButtons.get(i));
        }
    }

    public void setClickListerners(){
        for(int i=0; i<imageButtons.size(); i++){
            if(i==answer){
                imageButtons.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        generateRandomData();
                        setData();
                        setClickListerners();
                    }
                });
            }else {
                imageButtons.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
    }

    public void displayImage(String path, ImageView iv) {
        AssetManager as = getApplicationContext().getAssets();
        InputStream input;
        try {
            input = as.open(path);
            Drawable d = Drawable.createFromStream(input, null);
            iv.setImageDrawable(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSound(String sound_path){
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();

            AssetFileDescriptor descriptor = getApplicationContext().getAssets().openFd(sound_path);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            mediaPlayer.prepare();
            mediaPlayer.setVolume(1f, 1f);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
}