package com.project.beatbox;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button next;
    RelativeLayout rl;
    Cover cov;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        setVarialbles();
        cov=new Cover(this);
        ViewGroup.LayoutParams params1=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        cov.setLayoutParams(params1);
        rl.addView(cov);
        next.bringToFront();
        next.setOnClickListener(this);
    }

    private void setVarialbles() {
        next=(Button)findViewById(R.id.buttonNext);
        rl=(RelativeLayout)findViewById(R.id.relativeActivity);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(this,Pattern1.class);
        startActivity(i);
        finish();
    }
}
