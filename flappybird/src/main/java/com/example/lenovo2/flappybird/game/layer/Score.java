package com.example.lenovo2.flappybird.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.lenovo2.flappybird.game.GameSurface;
import com.example.lenovo2.flappybird.utils.Constants;

/**
 * Score
 *
 * @author: lenovo
 * @time: 2016/1/24 10:05
 */
public class Score extends BasePlayer {
    private float scoreX,scoreY;
    private int score;
    private int scoreMax;
    private long startTime;//游戏开始时间
    private boolean isStart;

    public Score(GameSurface surface) {
        super(surface);
        score=0;
        scoreX=screenW/2;
        scoreY=screenH/3;
        isStart=true;
       scoreMax=surface.getScoreMax();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        switch (surface.getGamaState()) {
            case Constants.GAME_START:
                canvas.drawText(scoreMax + "s", scoreX, scoreY, paint);
                break;
            case Constants.GAMEING:
                canvas.drawText(score + "s", scoreX, scoreY, paint);
                break;
            default:
                break;
        }


    }

    @Override
    public void logic() {
        if(isStart){
            startTime=System.currentTimeMillis();
            isStart=false;
        }
        long endTime=System.currentTimeMillis();
        score=(int)((endTime-startTime)/1000);
        if(score>scoreMax){
            surface.setScoreMax(score);
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }

    public int getScoreMax() {
        return scoreMax;
    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }
}
