package com.project.beatbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

/**
 * Created by hp on 10/8/2016.
 */
public class Beautiful extends View {

    int x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,x6,y6,l,w;
    Random r=new Random();
    Paint ourColor1,ourColor2,ourColor3,ourColor4,ourColor5,ourColor6;

    public Beautiful(Context context) {
        super(context);
        x1=r.nextInt(200);
        y1=r.nextInt(200);
        x2=r.nextInt(200);
        y2=r.nextInt(200);
        x3=r.nextInt(200);
        y3=r.nextInt(200);
        x4=r.nextInt(200);
        y4=r.nextInt(200);
        x5=r.nextInt(200);
        y5=r.nextInt(200);
        x6=r.nextInt(200);
        y6=r.nextInt(200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        w=canvas.getWidth();
        l=canvas.getHeight();
        canvas.drawColor(Color.rgb(12, 6, 106));
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
        if(y1<l){
            y1+=10;
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            x1=r.nextInt(w-40);
            y1=r.nextInt(l-200);
        }
        if(y2<l){
            y2+=10;
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            x2=r.nextInt(w-40);
            y2=r.nextInt(l-200);
        }
        if(y3<l){
            y3+=10;
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            x3=r.nextInt(w-40);
            y3=r.nextInt(l-200);
        }
        if(y4<l){
            y4+=10;
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            x4=r.nextInt(w-40);
            y4=r.nextInt(l-200);
        }
        if(y5<l){
            y5+=10;
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            x5=r.nextInt(w-40);
            y5=r.nextInt(l-200);
        }
        if(y6<l){
            y6+=10;
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            x6=r.nextInt(w-40);
            y6=r.nextInt(l-200);
        }
        invalidate();
    }
}
