package com.example.lenovo2.flappybird.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.lenovo2.flappybird.game.GameSurface;
import com.example.lenovo2.flappybird.utils.Assist;
import com.example.lenovo2.flappybird.utils.Constants;

/**
 * Player
 *
 * @author: lenovo
 * @time: 2016/1/24 10:01
 */
public class Player extends BasePlayer{


    private int playerx,playery;
    private int r;
    private int speed,acc;

    public Player(GameSurface surface) {

        super(surface);
        playerx=screenW/3;
        playery=screenH/2;
        r=60;
        speed=15;
        acc=2;

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(Color.BLACK);

        switch (surface.getGamaState()){
            case Constants.GAME_START:
                canvas.drawCircle(screenW/2,screenH/2,r,paint);
                break;
            case Constants.GAMEING:
                canvas.drawCircle(playerx,playery,r,paint);
                break;
            case Constants.GAME_END:
                canvas.drawCircle(screenW/2,screenH/2,r,paint);
                break;
            default:
                break;
        }
    }

    @Override
    public void logic() {
        playery+=speed;
        speed+=acc;
        //与上下边界碰撞
        if(playery-r<0||playery+r>screenH){
            surface.setGamaState(Constants.GAME_END);
        }

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        speed = -20;

    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }

    public int getPlayerx() {
        return playerx;
    }

    public int getPlayery() {
        return playery;
    }

    public int getR() {
        return r;
    }

}

