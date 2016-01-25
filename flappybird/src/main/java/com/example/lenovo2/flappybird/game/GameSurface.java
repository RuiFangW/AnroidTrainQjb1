package com.example.lenovo2.flappybird.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.lenovo2.flappybird.game.layer.Background;
import com.example.lenovo2.flappybird.game.layer.Barrier;
import com.example.lenovo2.flappybird.game.layer.Player;
import com.example.lenovo2.flappybird.game.layer.Score;
import com.example.lenovo2.flappybird.game.layer.Start;
import com.example.lenovo2.flappybird.utils.Constants;

/**
 * GameSurface
 *
 * @author: lenovo
 * @time: 2016/1/24 9:29
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    public final static String TAG="Game";
    public static long starttime,end;

    private static int gamaState;//游戏状态
    private SurfaceHolder holder;

    private Canvas canvas;
    private Paint paint;

    private Thread thread;
    private boolean flag;

    private Background background;//背景
    private Start start;
    private Player player;
    private Barrier barrier;
    private Score score;

    private int scoreMax=0;

    public GameSurface(Context context) {
        super(context);
        init();
    }

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        holder=getHolder();
        holder.addCallback(this);
        setKeepScreenOn(true);

        holder.setFormat(PixelFormat.TRANSPARENT);
        setZOrderOnTop(true);
        /*holder.setFormat(PixelFormat.TRANSPARENT);
        setKeepScreenOn(true);
        setZOrderOnTop(true);//与照相机共存*/
        paint=new Paint();
        paint.setAntiAlias(true);

    }
    private  void initGame(){
        gamaState= Constants.GAME_START;//设置初始状态为   游戏开始
        background=new Background(this);
        player=new Player(this);
        start=new Start(this);
        barrier=new Barrier(this);
        score=new Score(this);
        score.setScoreMax(scoreMax);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initGame();
        flag=true;
        thread=new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag=false;
    }


    //huatu
    private void myDraw(Canvas canvas){
        //background.draw(canvas,paint);
        //清屏
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        switch (gamaState){
            case Constants.GAME_START:
                player.draw(canvas, paint);
                start.draw(canvas, paint);
                score.draw(canvas, paint);
                break;
            case Constants.GAMEING:
                player.draw(canvas, paint);
                barrier.draw(canvas, paint);
                score.draw(canvas, paint);
                break;
            case Constants.GAME_END:

               player.draw(canvas,paint);
                start.draw(canvas, paint);
                break;
            default:
                break;
        }
    }
    //Luoji
    private void logic(){

        switch (gamaState) {
            case Constants.GAME_START:
                break;
            case Constants.GAMEING:
                player.logic();
                barrier.setPlayX(player.getPlayerx());
                barrier.setPlayY(player.getPlayery());
                barrier.setRadius(player.getR());
                barrier.logic();
                score.logic();
                break;
            case Constants.GAME_END:
                initGame();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (gamaState) {
            case Constants.GAME_START:
                start.onTouchEvent(event);
                break;
            case Constants.GAMEING:
                player.onTouchEvent(event);
                break;
            case Constants.GAME_END:
                start.onTouchEvent(event);

                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }


    public void run(){
        while(flag){
            long start=System.currentTimeMillis();
            canvas=holder.lockCanvas();
            if(null!=canvas){
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
            logic();
            long end=System.currentTimeMillis();
            if(end-start<50){
                try {
                    Thread.sleep(50-(end-start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getGamaState() {
        return gamaState;
    }

    public void setGamaState(int gamaState) {
        this.gamaState = gamaState;
    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

    public int getScoreMax() {
        return scoreMax;
    }
}
