package com.example.my_picture_match_game.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.my_picture_match_game.Adapter.Level_Display_Adapter;
import com.example.my_picture_match_game.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Level_Display_Activity extends AppCompatActivity {
    GridView level_gridview;
    TextView leveltype1;
    ImageView back1;
    TextView timeshow;
    int column;
    ProgressBar progressBar;
    String level;
    private ArrayList<String> imgarray = new ArrayList<>();
    private List<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_display);
        level_gridview = findViewById(R.id.level_gridview);
        back1= findViewById(R.id.back1);
        timeshow = findViewById(R.id.timeshow);
        progressBar=findViewById(R.id.progressBar);
        leveltype1=findViewById(R.id.leveltype1);


        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_Display_Activity.this, Level_Board_Activity.class);
                startActivity(intent);
            }
        });


       level=getIntent().getStringExtra("level");
        Log.d("AAA", "onCreate: level"+level);
        leveltype1.setText(level);

        MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(Level_Display_Activity.this);

        builder.setTitle("You Have 5 Seconds To Memorize All Images");
        builder.setPositiveButton("GO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int levelno = getIntent().getIntExtra("levelno", 1);

                String[] images = new String[0];
                try {
                    images = getAssets().list("pics");
                    imgarray = new ArrayList<String>(Arrays.asList(images));
                    imgarray.remove("android-logo-mask.png");
                    imgarray.remove("android-logo-shine.png");
                    imgarray.remove("clock_font.png");
                    imgarray.remove("progress_font.png");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (levelno <=3) {
                    arrayList = imgarray.subList(0, 6);
                    column = 3;
                }
                if (levelno >3&&levelno<=5) {
                    arrayList = imgarray.subList(0, 8);
                    column = 4;
                }
                if (levelno >5&&levelno<=8) {
                    arrayList = imgarray.subList(0, 10);
                    column = 4;
                }
                if (levelno>8&&levelno<=10)
                {
                    arrayList=imgarray.subList(0,12);
                    column=6;
                }


                arrayList.addAll(arrayList);

                Collections.shuffle(arrayList);
                Level_Display_Adapter adapter = new Level_Display_Adapter(Level_Display_Activity.this, arrayList,timeshow,progressBar,level);
                level_gridview.setNumColumns(column);
                level_gridview.setAdapter(adapter);
                dialog.dismiss();
            }
        });
        builder.show();


    }
}