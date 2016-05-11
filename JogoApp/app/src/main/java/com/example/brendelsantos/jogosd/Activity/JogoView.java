package com.example.brendelsantos.jogosd.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.brendelsantos.jogosd.R;

import java.util.Random;

class JogoView extends SurfaceView implements Runnable {

    Thread gameThread = null;
    SurfaceHolder ourHolder;

    volatile boolean playing;

    Canvas canvas;
    Paint paint;
    long fps;
    Random random;
    int pontuacao;

    int contadorObjetosRenovados = 0;

    private long timeThisFrame;

    Bitmap bitmapBackground;

    int[][] matrizImagem;
    int largura;
    int altura;

    Resources resources;
    Context contexto;

    public JogoView(Context context) {
        super(context);

        contexto = context;
        pontuacao = 0;

        ourHolder = getHolder();
        paint = new Paint();
        random = new Random();
        resources = context.getResources();

        bitmapBackground = BitmapFactory.decodeResource(this.getResources(), R.drawable.background);


        playing = true;

        matrizImagem = new int[largura][altura];
    }


    @Override
    public void run() {
        while (playing) {

            long startFrameTime = System.currentTimeMillis();
            update();
            draw();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                fps = 1000 / timeThisFrame;
            }

        }

    }

    public void update() {

    }

    public void draw() {

        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();

            canvas.drawBitmap(bitmapBackground, 0, 0, paint);
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:


                break;

            case MotionEvent.ACTION_UP:


                break;
        }
        return true;
    }

}