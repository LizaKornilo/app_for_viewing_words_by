package com.example.cwversion3;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cwversion3.database.entities.Lesson;
import com.example.cwversion3.database.entities.Theme;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class ThemesAndLessonsAdapter extends BaseExpandableListAdapter {
    Context context;
    List<Theme> listGroupe;
    HashMap<Theme, List<Lesson>> listChild;

    public ThemesAndLessonsAdapter(Context context, List<Theme> themes, HashMap<Theme, List<Lesson>> themes_lessons) {
        this.context = context;
        this.listGroupe = themes;
        this.listChild = themes_lessons;
    }

    @Override
    public int getGroupCount() {
        return listGroupe.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listChild.get(listGroupe.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroupe.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChild.get(listGroupe.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Theme theme = (Theme) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.theme_item, null);
        }
        TextView tv = convertView.findViewById(R.id.tvThemeName);
        tv.setText(theme.getName());
        ImageView iv = convertView.findViewById(R.id.ivThemeImage);
        displayImage(App.getInstance().findImagePathById(theme.getImage_id()), iv);

        ImageView ivIndicator = convertView.findViewById(R.id.indicatorHolder);
        if(isExpanded){
            ivIndicator.setImageResource(R.drawable.iconup);
        }else {ivIndicator.setImageResource(R.drawable.icondown);}

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Lesson lesson = (Lesson) getChild(groupPosition, childPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.lesson_item, null);
        }

        TextView tv = convertView.findViewById(R.id.tv_lesson_name);
        tv.setText(lesson.getName());
        ImageView iv = convertView.findViewById(R.id.ivLessonImage);
        displayImage(App.getInstance().findImagePathById(lesson.getImage_id()), iv);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WordActivity.class);
                intent.putExtra("lesson_id", lesson.getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void displayImage(String path, ImageView iv){
        AssetManager as = context.getAssets();
        InputStream input;
        try {
            input = as.open(path);
            Drawable d = Drawable.createFromStream(input, null);
            iv.setImageDrawable(d);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
