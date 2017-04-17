package com.project.beatbox;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by hp on 10/4/2016.
 */
public class StartMusic extends AppCompatActivity implements View.OnClickListener {

    PowerManager.WakeLock wl;
    Beautiful ourView;
    Button stop;
    int[] trackArray;
    MediaPlayer B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B14,B15,B16;
    MyThread th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerManager pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
        wl=pm.newWakeLock(PowerManager.FULL_WAKE_LOCK,"whatever");
        wl.acquire();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ourView=new Beautiful(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        setContentView(R.layout.startmusic);
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.relativeActivityStart);
        ViewGroup.LayoutParams pp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ourView.setLayoutParams(pp);
        rl.addView(ourView);

        stop=(Button)findViewById(R.id.buttonStop);
        stop.setOnClickListener(this);
        stop.bringToFront();

        Bundle gotb=getIntent().getExtras();
        trackArray=gotb.getIntArray("track");
        setVariables();
        playmusic(trackArray);
    }

    private class MyThread extends Thread{

        private volatile boolean x=true;
        int[] songs=new int[256];
        int c=0;
        public MyThread(int[] trackList){
            for(int j=0;j<256;j++){
                if(trackList[j]!=0){
                    songs[c]=trackList[j];
                    c++;
                }
            }
        }

        public void stopIt(){
            x=false;
        }

        @Override
        public void run() {
            while(x){
                musicPlaying(songs,c);
            }
        }
    }

    private void musicPlaying(int[] songs, int c) {
        for(int i=0;i<c;i++){
            int xyz=songs[i];
            switch (xyz){
                case 1:
                    B1.start();
                    break;
                case 2:
                    B2.start();
                    break;
                case 3:
                    B3.start();
                    break;
                case 4:
                    B4.start();
                    break;
                case 5:
                    B5.start();
                    break;
                case 6:
                    B6.start();
                    break;
                case 7:
                    B7.start();
                    break;
                case 8:
                    B8.start();
                    break;
                case 9:
                    B9.start();
                    break;
                case 10:
                    B10.start();
                    break;
                case 11:
                    B11.start();
                    break;
                case 12:
                    B12.start();
                    break;
                case 13:
                    B13.start();
                    break;
                case 14:
                    B14.start();
                    break;
                case 15:
                    B15.start();
                    break;
                case 16:
                    B16.start();
                    break;
            }
        }
    }

    private void setVariables() {
        B1=MediaPlayer.create(this, R.raw.beat1);
        B2=MediaPlayer.create(this, R.raw.beat2);
        B3=MediaPlayer.create(this, R.raw.beat3);
        B4=MediaPlayer.create(this, R.raw.beat4);
        B5=MediaPlayer.create(this, R.raw.beat5);
        B6=MediaPlayer.create(this, R.raw.beat6);
        B7=MediaPlayer.create(this, R.raw.beat7);
        B8=MediaPlayer.create(this, R.raw.beat8);
        B9=MediaPlayer.create(this, R.raw.beat9);
        B10=MediaPlayer.create(this, R.raw.beat10);
        B11=MediaPlayer.create(this, R.raw.beat11);
        B12=MediaPlayer.create(this, R.raw.beat12);
        B13=MediaPlayer.create(this, R.raw.beat13);
        B14=MediaPlayer.create(this, R.raw.beat14);
        B15=MediaPlayer.create(this, R.raw.beat15);
        B16=MediaPlayer.create(this, R.raw.beat16);
    }

    private void playmusic(int[] trackArray) {
        th=new MyThread(trackArray);
        th.start();
    }

    @Override
    public void onClick(View v) {
        th.stopIt();
        finish();
    }
}
