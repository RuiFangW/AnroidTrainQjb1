package com.example.lenovo2.flappybird.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.lenovo2.flappybird.game.GameSurface;
import com.example.lenovo2.flappybird.utils.Constants;

import java.util.Random;

/**
 * Barrier
 *
 * @author: lenovo
 * @time: 2016/1/24 10:03
 */
public class Barrier extends BasePlayer{

    private float spaceH;//障碍上下间隔
    private float distance;//左右距离
    private float barrierW;//障碍物的宽
    private float barrierY;//障碍物的Y
    private float speed;//障碍移动的距离

    private float barrier1X,barrier2X;//障碍物的x
    private float barrier1H,barrier2H;//障碍物的高

    private float playX,playY,radius;//主角的x,y,r
    public Barrier(GameSurface surface) {
        super(surface);
        speed=15;
        spaceH=screenH/4;
        barrierW=130;
        barrierY=0;
        distance=screenW/2-barrierW/2;

        barrier1X=screenW +200;
        barrier1H=getBarrierHRandom();


        barrier2X=barrier1X+distance+barrierW;
        barrier2H=getBarrierHRandom();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(Color.GREEN);
        //第一个障碍上
        canvas.drawRect(barrier1X, barrierY, barrier1X + barrierW, barrierY + barrier1H, paint);
        //第一个障碍下
        canvas.drawRect(barrier1X, barrier1H + spaceH, barrier1X+barrierW,screenH,paint);
        //第二个障碍上
        canvas.drawRect(barrier2X,barrierY,barrier2X+barrierW,barrierY+barrier2H,paint);
        //第二个障碍下
        canvas.drawRect(barrier2X,barrier2H+spaceH,barrier2X+barrierW,screenH,paint);

    }

    @Override
    public void logic() {

        barrier1X-=speed;
        barrier2X-=speed;
        if(barrier1X+barrierW<0){
            barrier1X=screenW;
            barrier1H=new Random().nextInt((int)(screenH-spaceH-100));
        }
        if(barrier2X+barrierW<0){
            barrier2X=screenW;
            barrier2H=new Random().nextInt((int)(screenH-spaceH-100));
        }
        boolean isColl1=circleAndRect(playX,playY,radius,barrier1X,barrierY,barrierW,barrier1H);
        boolean isColl2=circleAndRect(playX,playY,radius,barrier1X,barrier1H+spaceH,barrierW,screenH-barrier1H-spaceH);
        boolean isColl3=circleAndRect(playX,playY,radius,barrier2X,barrierY,barrierW,barrier2H);
        boolean isColl4=circleAndRect(playX,playY,radius,barrier2X,barrier2H+spaceH,barrierW,screenH-barrier2H-spaceH);
        if(isColl1||isColl2||isColl3||isColl4){
            surface.setGamaState(Constants.GAME_END);
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }
    private float getBarrierHRandom(){
        return new Random().nextInt((int)(screenH-spaceH-100));
    }

    public void setPlayX(float playX) {
        this.playX = playX;
    }

    public void setPlayY(float playY) {
        this.playY = playY;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
    private boolean circleAndRect(float circleX, float circleY, float circleR, float rectX, float
            rectY, float rectW, float rectH) {
        if (circleX + circleR < rectX) {
            return false;
        } else if (circleX - circleR > rectX + rectW) {
            return false;
        } else if (circleY + circleR < rectY) {
            return false;
        } else if (circleY - circleR > rectY + rectH) {
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY < rectX) {
            return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX > rectX + rectW && circleY < rectY) {
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY > rectY + rectH) {
            return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) >
                circleR * circleR && circleX > rectX + rectW && circleY > rectY + rectH) {
            return false;
        }

        return true;
    }

}
