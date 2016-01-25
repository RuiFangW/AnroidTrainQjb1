package com.example.lenovo2.flappybird.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.lenovo2.flappybird.game.GameSurface;
import com.example.lenovo2.flappybird.utils.Constants;

/**
 * Start
 *
 * @author: lenovo
 * @time: 2016/1/24 10:02
 */
public class Start extends BasePlayer {
    protected  float x,y;
    protected float w,h;
    protected float trianleW,trianleH;


    public Start(GameSurface surface) {

        super(surface);
        w=250;
        h=150;
        x=screenW/2-w/2;
        y=2*screenH/3-h/2;
        trianleW=80;
        trianleH=50;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(x, y, x + w, y + h, paint);
        paint.setColor(Color.GREEN);
        Path path=new Path();
        path.moveTo(x + w / 2 - trianleH / 2, y + h / 2 - trianleW / 2);
        path.lineTo(x + w / 2 - trianleH / 2, y + h / 2 +trianleW / 2);
        path.lineTo(x + w / 2 + trianleH / 2, y + h / 2);
        canvas.drawPath(path, paint);


    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        int touchX=(int)event.getX();
        int touchY=(int)event.getY();
        // 判断是否点击了开始按钮
        if(touchX>x&&touchX<x+w&&touchY>y&&touchY<y+h){
            surface.setGamaState(Constants.GAMEING);

        }
    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }
}
