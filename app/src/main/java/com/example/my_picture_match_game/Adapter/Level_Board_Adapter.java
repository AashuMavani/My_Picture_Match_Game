package com.example.my_picture_match_game.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_picture_match_game.Activity.Level_Board_Activity;
import com.example.my_picture_match_game.Activity.Level_Display_Activity;
import com.example.my_picture_match_game.R;

public class Level_Board_Adapter extends RecyclerView.Adapter<Level_Board_Adapter.LevelBordHolder> {
    Level_Board_Activity level_board_activity;
    String level;

    public Level_Board_Adapter(Level_Board_Activity level_board_activity, String level ) {
        this.level_board_activity = level_board_activity;
        this.level=level;

    }

    @NonNull
    @Override
    public Level_Board_Adapter.LevelBordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(level_board_activity).inflate(R.layout.level_bord_item_layout,parent,false);
        LevelBordHolder holder=new LevelBordHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Level_Board_Adapter.LevelBordHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class LevelBordHolder extends RecyclerView.ViewHolder {
        ListView listView;
        public LevelBordHolder(@NonNull View itemView) {
            super(itemView);
          listView=itemView.findViewById(R.id.listview);
          Listview_Adapter adapter=new Listview_Adapter(level_board_activity);
          listView.setAdapter(adapter);
          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Intent intent=new Intent(level_board_activity,Level_Display_Activity.class);
                  intent.putExtra("levelno",position+1);
                  intent.putExtra("level",level);
                  level_board_activity.startActivity(intent);
              }
          });
        }
    }
}
