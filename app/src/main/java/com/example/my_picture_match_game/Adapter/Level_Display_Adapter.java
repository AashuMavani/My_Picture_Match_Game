package com.example.my_picture_match_game.Adapter;

import static com.example.my_picture_match_game.Activity.MainActivity.editor;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_picture_match_game.Activity.Level_Board_Activity;
import com.example.my_picture_match_game.Activity.Level_Display_Activity;
import com.example.my_picture_match_game.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Level_Display_Adapter extends BaseAdapter {
    Level_Display_Activity level_display_activity;
    List arrayList;
    TextView timeshow;
    ProgressBar progressBar;
    int click = 1, pos1, pos2,win=0;
    View mask2;
    int intervel;
    Runnable runnable;
    String level;
    private long delytime=40000;
    private int maxtime=40;

    public Level_Display_Adapter(Level_Display_Activity level_display_activity, List arrayList, TextView timeshow, ProgressBar progressBar, String level) {
        this.level_display_activity = level_display_activity;
        this.arrayList = arrayList;
        this.timeshow = timeshow;
        this.progressBar = progressBar;
        this.level=level;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        view = LayoutInflater.from(level_display_activity).inflate(R.layout.gridview_item, parent, false);
        ImageView imageView = view.findViewById(R.id.gridview_item);
        View mask1 = view.findViewById(R.id.mask1);
        RelativeLayout relativeLayout = view.findViewById(R.id.relative);

        Log.d("MMM", "getView: List=" + arrayList);

        InputStream inputStream = null;
        try {
            inputStream = level_display_activity.getAssets().open("pics/" + arrayList.get(position));
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
            inputStream.close();
            Log.d("MMM", "getView: Position=" + position);

        } catch (IOException e) {
            new RuntimeException(e);
        }
        startCountDown(mask1, relativeLayout, position);
        return view;
    }

    private void startCountDown(View mask1, RelativeLayout relativeLayout, int position) {
        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setMax(5);
                int time = (int) (millisUntilFinished / 1000);
                timeshow.setText("" + time + "/5");
                progressBar.setProgress(time);
            }

            @Override
            public void onFinish() {
                playgame(mask1, relativeLayout, position);
               if(level.equals("NO_TIME_LIMIT"))
               {
                   new CountDownTimer(30000, 1000) {

                       @Override
                       public void onTick(long millisUntilFinished) {
                           progressBar.setMax(30);
                           intervel = (int) (millisUntilFinished / 1000);
                           timeshow.setText("" + (progressBar.getMax() - intervel) + "/30");
                           progressBar.setProgress(progressBar.getMax() - intervel);

                       }

                       @Override
                       public void onFinish() {

                       }
                   }.start();
               }
               if(level.equals("NORMAL"))
               {
                   new CountDownTimer(30000, 1000) {

                       @Override
                       public void onTick(long millisUntilFinished) {
                           progressBar.setMax(30);
                           int time = (int) (millisUntilFinished / 1000);
                           timeshow.setText("" + (progressBar.getMax() - time) + "/30");
                           progressBar.setProgress(progressBar.getMax() - time);
                       }

                       @Override
                       public void onFinish() {
                           MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(level_display_activity);
                           builder.setTitle("Try Again");
                           builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   Intent intent=new Intent(level_display_activity,Level_Display_Activity.class);
                                   level_display_activity.startActivity(intent);
                               }
                           });
                           builder.show();
                           builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   Intent intent=new Intent(level_display_activity, Level_Board_Activity.class);
                                   level_display_activity.startActivity(intent);
                               }
                           });
                           builder.show();
                           relativeLayout.setEnabled(false);

                       }
                   }.start();
               }
               if (level.equals("HARD"))
               {
                   new CountDownTimer(20000, 1000) {

                       @Override
                       public void onTick(long millisUntilFinished) {
                           progressBar.setMax(20);
                           int time = (int) (millisUntilFinished / 1000);
                           timeshow.setText("" + (progressBar.getMax() - time) + "/20");
                           progressBar.setProgress(progressBar.getMax() - time);
                       }

                       @Override
                       public void onFinish() {
                           MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(level_display_activity);
                           builder.setTitle("Try Again");
                           builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   Intent intent=new Intent(level_display_activity,Level_Display_Activity.class);
                                   level_display_activity.startActivity(intent);
                               }
                           });
                           builder.show();
                           builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   Intent intent=new Intent(level_display_activity, Level_Board_Activity.class);
                                   level_display_activity.startActivity(intent);
                               }
                           });
                           builder.show();
                           relativeLayout.setEnabled(false);

                       }
                   }.start();
               }
            }


        }.start();
    }

    private void playgame(View mask1, RelativeLayout relativeLayout, int position) {
        mask1.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        if (click == 1) {
            relativeLayout.setOnClickListener(v -> {
                if (click == 1) {
                    mask1.setVisibility(View.INVISIBLE);
                    pos1 = position;
                    mask2 = mask1;
                    click = 3;
                    Log.d("RRR", "playGame: FirstClick=" + click);
                    runnable = new Runnable() {
                        public void run() {
                            click = 2;
                            Log.d("RRR", "playGame: Click=" + click);
                        }
                    };

                    handler.postDelayed(runnable, 300);

                }
                if (click == 2) {
                    mask1.setVisibility(View.INVISIBLE);
                    pos2 = position;
                    click = 3;
                    Log.d("RRR", "playGame: SecondClick=" + click);
                    if (arrayList.get(pos1).equals(arrayList.get(pos2))) {
                        win++;

                        if(win==arrayList.size()/2)
                        {

                           ///Toast.makeText(level_display_activity, "win", Toast.LENGTH_SHORT).show();
                            Log.d("TTT", "playgame: time="+(progressBar.getMax()-intervel));
                            int second= (int) (delytime=(progressBar.getMax()-intervel));
                            editor.putInt("wintime",second);
                            editor.putInt("levelno",position);
                            editor.commit();

                            MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(level_display_activity);
                            builder.setTitle("LEVEL COMPLETED");
                            builder.setMessage(progressBar.getMax()-intervel+  "SECONDS TO COMPLETE THIS LEVEL..\nCONGRATULATIONS!!!");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(level_display_activity,Level_Board_Activity.class);
                                    level_display_activity.startActivity(intent);
                                }
                            });
                            builder.show();
                            delytime=(progressBar.getMax()-intervel);
                        }
                        Log.d("RRR", "playGame: Matched");
                        runnable = new Runnable() {
                            public void run() {
                                click = 1;
                                Log.d("RRR", "playGame: Click=" + click);
                            }
                        };

                    } else {

                        Log.d("RRR", "playGame: NotMatched");
                        runnable = new Runnable() {
                            public void run() {
                                mask1.setVisibility(View.VISIBLE);
                                mask2.setVisibility(View.VISIBLE);
                                click = 1;
                            }
                        };

                    }
                    handler.postDelayed(runnable, 300);
                }
            });

        }
    }

}
