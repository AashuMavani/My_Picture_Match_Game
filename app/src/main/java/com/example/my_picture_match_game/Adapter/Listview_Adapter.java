package com.example.my_picture_match_game.Adapter;

import static com.example.my_picture_match_game.Activity.MainActivity.preferences;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.my_picture_match_game.Activity.Level_Board_Activity;
import com.example.my_picture_match_game.Activity.MainActivity;
import com.example.my_picture_match_game.R;

import java.util.prefs.Preferences;

public class Listview_Adapter extends BaseAdapter {
    Level_Board_Activity level_board_activity;
    String[]levelno={"Level 1","Level 2","Level 3","Level 4","Level 5","Level 6","Level 7","Level 8","Level 9","Level 10"};
   int second;
    public Listview_Adapter(Level_Board_Activity level_board_activity) {
        this.level_board_activity = level_board_activity;

    }

    @Override
    public int getCount() {
        return levelno.length ;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
         view= LayoutInflater.from(level_board_activity).inflate(R.layout.listview_item,parent,false);
        TextView textView=view.findViewById(R.id.listview_item);

        int wintime=preferences.getInt("wintime"+(position+1),0);

        System.out.println("LevelNo="+position);
        System.out.println("wintime="+wintime);

//        int levelno1=preferences.getInt("levelno",1);

        textView.setText(levelno[position]+"-"+wintime);

        return view;
    }
}
