package com.example.cwversion3;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class WordFragment extends Fragment {
    private static final String IMAGE_PATH = "image_path";
    private static final String WORD_BY = "word_by";
    private static final String SOUND_PATH = "sound_path";

    private String image_path;
    private String word_by;
    private String sound_path;

    public WordFragment() {
        // Required empty public constructor
    }

    public static WordFragment newInstance(String image_path, String word_by, String sound_path) {
        WordFragment fragment = new WordFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_PATH, image_path);
        args.putString(WORD_BY, word_by);
        args.putString(SOUND_PATH, sound_path);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image_path = getArguments().getString(IMAGE_PATH);
            word_by = getArguments().getString(WORD_BY);
            sound_path = getArguments().getString(SOUND_PATH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word, container, false);
        ImageView iv = (ImageView) view.findViewById(R.id.ivWordImsge);
        TextView tv = (TextView) view.findViewById(R.id.tvWordBy);
        tv.setVisibility(TextView.INVISIBLE);
        ImageButton ib = (ImageButton) view.findViewById(R.id.ivSoundIcon);

        displayImage(image_path, iv);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(sound_path);
                TextView tv = (TextView) getView().findViewById(R.id.tvWordBy);
                showWordBY(word_by, tv);
            }
        });
        return view;
    }

    public void showWordBY(String word_by, TextView tv){
        tv.setText(word_by);
        tv.setVisibility(TextView.VISIBLE);
    }

    public void displayImage(String path, ImageView iv){
        AssetManager as = getContext().getAssets();
        InputStream input;
        try {
            input = as.open(path);
            Drawable d = Drawable.createFromStream(input, null);
            iv.setImageDrawable(d);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playSound(String sound_path){
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();

            AssetFileDescriptor descriptor = getContext().getAssets().openFd(sound_path);
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