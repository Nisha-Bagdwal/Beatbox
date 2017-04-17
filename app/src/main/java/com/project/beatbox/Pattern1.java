package com.project.beatbox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.widget.Toast.*;

public class Pattern1 extends AppCompatActivity implements View.OnClickListener,SlidingDrawer.OnDrawerCloseListener,SlidingDrawer.OnDrawerOpenListener{

    private static final int SELECT_FILE = 1;
    private static final int SELECT_IMAGE = 2;
    ImageView fl;
    Drawable draw;
    Bitmap bitmap;
    GridLayout grid;
    Boolean[] checkBoxState;
    EditText et;
    AppCompatButton start,noPattern,openPattern,savePattern,handle;
    AppCompatCheckBox chk;
    SlidingDrawer sd;
    File path=null,file=null;
    String[] beats={"B1","B2","B3","B4","B5","B6","B7","B8","B9","B10","B11","B12","B13","B14","B15","B16"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Activity starts as landscape
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.pattern1);

        fl=(ImageView) findViewById(R.id.imageBackground);
        getThumbnail("myBackground.png");

        setVariables();
        setGrids();

        sd=(SlidingDrawer)findViewById(R.id.slidingD);
        handle=(AppCompatButton)findViewById(R.id.handle);

        sd.setOnDrawerCloseListener(this);
        sd.setOnDrawerOpenListener(this);

        start.setOnClickListener(this);
        noPattern.setOnClickListener(this);
        openPattern.setOnClickListener(this);
        savePattern.setOnClickListener(this);
    }

    private void setVariables(){
        grid=(GridLayout)findViewById(R.id.gridPattern);
        start=(AppCompatButton) findViewById(R.id.bStart);
        noPattern=(AppCompatButton)findViewById(R.id.bNoPattern);
        openPattern=(AppCompatButton)findViewById(R.id.bOpenPattern);
        savePattern=(AppCompatButton)findViewById(R.id.bSavePattern);
    }

    private  void setGrids(){
        for(int i=0;i<16;i++){
            TextView tx=new TextView(Pattern1.this);
            tx.setText(beats[i]);
            grid.addView(tx);
            for(int j=1;j<17;j++){
                chk=new AppCompatCheckBox(Pattern1.this);
                chk.setChecked(false);
                grid.addView(chk);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater blowUp=getMenuInflater();
        blowUp.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.itemAbout:
                Intent in=new Intent(Pattern1.this,AboutUs.class);
                startActivity(in);
                break;
            case R.id.itemExit:
                finish();
                break;
            case R.id.itemChange:
                Intent chooser=new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath().toString());
                chooser.addCategory(Intent.CATEGORY_OPENABLE);
                chooser.setDataAndType(uri,"image/*");
                try{
                    startActivityForResult(chooser,SELECT_IMAGE);
                }catch(android.content.ActivityNotFoundException ex){
                    Toast.makeText(this,"Please install a gallery", LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerClosed() {
        handle.setText("Slide Up");
    }

    @Override
    public void onDrawerOpened() {
        handle.setText("Slide Down");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bStart:
                int trackArray[]=new int[256];
                int c=0;
                for(int i=0;i<16;i++){
                    int key=i+1;
                    for(int j=1;j<17;j++){
                        chk=(AppCompatCheckBox)grid.getChildAt(j+(17*i));
                        if(chk.isChecked()){
                            trackArray[c]=key;
                        }else{
                            trackArray[c]=0;
                        }
                        c++;
                    }
                }
                Bundle bun=new Bundle();
                bun.putIntArray("track",trackArray);
                Intent in=new Intent(Pattern1.this,StartMusic.class);
                in.putExtras(bun);
                startActivity(in);
                break;

            case R.id.bNoPattern:
                for(int i=0;i<16;i++){
                    for(int j=1;j<17;j++){
                        chk=(AppCompatCheckBox) grid.getChildAt(j+(17*i));
                        chk.setChecked(false);
                    }
                }
                break;

            case R.id.bSavePattern:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.custom_dialog);

                dialog.setTitle("Save As...");
                et=(EditText)dialog.findViewById(R.id.ddet);
                AppCompatButton button = (AppCompatButton) dialog.findViewById(R.id.ddbt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String f=et.getText().toString();
                        saveEverything(f);
                        dialog.dismiss();
                        Toast t=Toast.makeText(Pattern1.this, "Pattern "+f+" is saved to BeatBox folder",Toast.LENGTH_LONG);
                        t.show();
                    }
                });
                dialog.show();
                break;

            case R.id.bOpenPattern:
                Intent chooser=new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath().toString());
                chooser.addCategory(Intent.CATEGORY_OPENABLE);
                chooser.setDataAndType(uri,"file/*");
                try{
                    startActivityForResult(chooser,SELECT_FILE);
                }catch(android.content.ActivityNotFoundException ex){
                    Toast.makeText(Pattern1.this,"Please install a File Manager",Toast.LENGTH_SHORT).show();
                }
        }
    }


    private void saveEverything(String f) {
        checkBoxState=new Boolean[256];
        int cn=0;
        for(int i=0;i<16;i++){
            for(int j=1;j<17;j++){
                chk=(AppCompatCheckBox) grid.getChildAt(j+(17*i));
                if(chk.isChecked()){
                    checkBoxState[cn]=true;
                }else
                    checkBoxState[cn]=false;
                cn++;
            }
        }
        path=new File(Environment.getExternalStorageDirectory(),"BeatBox");
        if(!path.exists())
            path.mkdirs();
        file=new File(path,f);
        try{
            FileOutputStream fs=new FileOutputStream(file);
            ObjectOutputStream os= new ObjectOutputStream(fs);
            os.writeObject(checkBoxState);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode){
                case SELECT_FILE:
                    if(resultCode==RESULT_OK){
                        String fpath=data.getData().getPath();
                        File fil=new File(fpath);
                        openFile(fil);
                        int cn=0;
                        for(int i=0;i<16;i++){
                            for(int j=1;j<17;j++){
                                chk=(AppCompatCheckBox) grid.getChildAt(j+(17*i));
                                if(checkBoxState[cn])
                                    chk.setChecked(true);
                                else
                                    chk.setChecked(false);
                                cn++;
                            }
                        }
                        Toast.makeText(Pattern1.this,"Selected Pattern is created",Toast.LENGTH_LONG).show();
                    }
                    break;
                case SELECT_IMAGE:
                    if(resultCode==RESULT_OK){
                        try{
                            if(bitmap!=null){
                                bitmap.recycle();
                            }
                            InputStream is=getContentResolver().openInputStream(data.getData());
                            bitmap=BitmapFactory.decodeStream(is);
                            is.close();
                            saveImage(bitmap);
                            getThumbnail("myBackground.png");
                            Toast.makeText(Pattern1.this,"Background Changed",Toast.LENGTH_SHORT).show();
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }else
                        Toast.makeText(Pattern1.this,"Unable to load image",Toast.LENGTH_LONG).show();
                    break;
            }
        }else{
            Toast.makeText(Pattern1.this,"Unable to load file",Toast.LENGTH_LONG).show();
        }
    }

    public boolean saveImage(Bitmap image){
        try {
            FileOutputStream fos=this.openFileOutput("myBackground.png", Context.MODE_PRIVATE);
            image.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.close();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

    public Bitmap getThumbnail(String fileName){
        Bitmap thumbnail=null;
        try {
            File file = this.getFileStreamPath(fileName);
            if(file.exists()){
                FileInputStream fis=new FileInputStream(file);
                thumbnail= BitmapFactory.decodeStream(fis);
                draw= new BitmapDrawable(getResources(),thumbnail);
                draw.setAlpha(100);
                fl.setImageDrawable(draw);
            }else{
                draw=getResources().getDrawable(R.drawable.image1);
                draw.setAlpha(100);
                fl.setImageDrawable(draw);
            }
        }catch(Exception ex){
            ex.printStackTrace();;
        }
        return thumbnail;
    }
    private void openFile(File fil) {
        try {
            checkBoxState = new Boolean[256];
            FileInputStream fs = new FileInputStream(fil);
            ObjectInputStream os = new ObjectInputStream(fs);
            checkBoxState = (Boolean[]) os.readObject();
            os.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}