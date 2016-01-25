package com.example.lenovo2.flappybird.game.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.lenovo2.flappybird.game.GameSurface;

/**
 * BasePlayer
 *
 * @author: lenovo
 * @time: 2016/1/24 10:13
 */
public abstract class BasePlayer {
    protected GameSurface surface;
    protected int screenW,screenH;//屏幕的宽和高
    protected Resources res;

    public BasePlayer(GameSurface surface){
        this.surface=surface;
        this.screenW=surface.getWidth();
        this.screenH=surface.getHeight();
        res = surface.getResources();
    }
    /**
     * 画图
     * @param canvas 画布
     * @param paint 画笔
     */
    public abstract void draw(Canvas canvas,Paint paint);

    /**
     * 逻辑
     */
    public abstract void logic();

    /**
     * 触摸事件
     *
     * @param event
     */
    public abstract void onTouchEvent(MotionEvent event);

    /**
     * 案件点击事件
     * @param keyCode
     * @param event
     */
    public abstract void onKeyDown(int keyCode, KeyEvent event);
}
