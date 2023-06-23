package com.example.my_picture_match_game.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my_picture_match_game.Adapter.Level_Board_Adapter;
import com.example.my_picture_match_game.R;

public class Level_Board_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView back;
    String level;
    int second;
    TextView leveltype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_board);
        recyclerView=findViewById(R.id.recyclerview);
        back=findViewById(R.id.back);
        leveltype=findViewById(R.id.leveltype);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Level_Board_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        level=getIntent().getStringExtra("level");
        Log.d("AAA", "onClick: level"+level);
        leveltype.setText(level);
        Level_Board_Adapter adapter=new Level_Board_Adapter(Level_Board_Activity.this,level);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}