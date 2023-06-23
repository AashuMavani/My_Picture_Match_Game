package com.example.my_picture_match_game.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.my_picture_match_game.R;

public class MainActivity extends AppCompatActivity {
    TextView no_time_limit,normal,hard;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    int lastlevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        no_time_limit=findViewById(R.id.no_time_limit);
        normal=findViewById(R.id.normal);
        hard=findViewById(R.id.hard);

        preferences=getSharedPreferences("mypref",MODE_PRIVATE);
        editor=preferences.edit();

        no_time_limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Level_Board_Activity.class);
                intent.putExtra("level","NO_TIME_LIMIT");
                startActivity(intent);
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Level_Board_Activity.class);
                intent.putExtra("level","NORMAL");
                startActivity(intent);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Level_Board_Activity.class);
                intent.putExtra("level","HARD");
                startActivity(intent);
            }
        });
    }
}