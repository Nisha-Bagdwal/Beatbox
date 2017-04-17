package com.project.beatbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

import java.util.Random;

/**
 * Created by hp on 10/4/2016.
 */
public class Cover extends View {
    int x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,x6,y6,l,w;
    Random r=new Random();
    Typeface font;
    Paint ourColor1,ourColor2,ourColor3,ourColor4,ourColor5,ourColor6;

    public Cover(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        x1=r.nextInt(300);
        y1=r.nextInt(300);
        x2=r.nextInt(300);
        y2=r.nextInt(300);
        x3=r.nextInt(300);
        y3=r.nextInt(300);
        x4=r.nextInt(300);
        y4=r.nextInt(300);
        x5=r.nextInt(300);
        y5=r.nextInt(300);
        x6=r.nextInt(300);
        y6=r.nextInt(300);
        font=Typeface.createFromAsset(context.getAssets(), "Machine.ttf");
    }

    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        w=canvas.getWidth();
        l=canvas.getHeight();
        canvas.drawColor(Color.rgb(0,0,0));
        ourColor1= new Paint();
        ourColor2= new Paint();
        ourColor3= new Paint();
        ourColor4= new Paint();
        ourColor5= new Paint();
        ourColor6= new Paint();
        ourColor1.setColor(Color.rgb(r.nextInt(256), r.nextInt(256),r.nextInt(256)));
        ourColor2.setColor(Color.rgb(r.nextInt(256), r.nextInt(256),r.nextInt(256)));
        ourColor3.setColor(Color.rgb(r.nextInt(256), r.nextInt(256),r.nextInt(256)));
        ourColor4.setColor(Color.rgb(r.nextInt(256), r.nextInt(256),r.nextInt(256)));
        ourColor5.setColor(Color.rgb(r.nextInt(256), r.nextInt(256),r.nextInt(256)));
        ourColor6.setColor(Color.rgb(r.nextInt(256), r.nextInt(256),r.nextInt(256)));

        canvas.drawRect(x1, y1, x1+40, y1+40, ourColor1);
        canvas.drawRect(x2, y2, x2+40, y2+40, ourColor2);
        canvas.drawRect(x3, y3, x3+40, y3+40, ourColor3);
        canvas.drawRect(x4, y4, x4+40, y4+40, ourColor4);
        canvas.drawRect(x5, y5, x5+40, y5+40, ourColor5);
        canvas.drawRect(x6, y6, x6+40, y6+40, ourColor6);
        try {
            Thread.sleep(3);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        x1=r.nextInt(w-40);
        y1=r.nextInt(l-40);

        try {
            Thread.sleep(3);
        } catch (InterruptedException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        x2=r.nextInt(w-40);
        y2=r.nextInt(l-40);

        try {
            Thread.sleep(3);
        } catch (InterruptedException e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }
        x3=r.nextInt(w-40);
        y3=r.nextInt(l-40);

        try {
            Thread.sleep(3);
        } catch (InterruptedException e4) {
            // TODO Auto-generated catch block
            e4.printStackTrace();
        }
        x4=r.nextInt(w-40);
        y4=r.nextInt(l-40);

        try {
            Thread.sleep(3);
        } catch (InterruptedException e5) {
            // TODO Auto-generated catch block
            e5.printStackTrace();
        }
        x5=r.nextInt(w-40);
        y5=r.nextInt(l-40);
        try {
            Thread.sleep(3);
        } catch (InterruptedException e6) {
            // TODO Auto-generated catch block
            e6.printStackTrace();
        }
        x6=r.nextInt(w-40);
        y6=r.nextInt(l-40);

        Paint textPaint=new Paint();
        textPaint.setColor(Color.rgb(255,255,255));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(100);
        textPaint.setTypeface(font);
        canvas.drawText("BeatBox", w/2, l/2, textPaint);

        invalidate();
    }
}
