package com.example.brendelsantos.jogosd.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.brendelsantos.jogosd.Componentes.PaintCartaJogo;
import com.example.brendelsantos.jogosd.Model.Carta;
import com.example.brendelsantos.jogosd.R;
import com.example.brendelsantos.jogosd.Util.PosicoesCartas;

import java.util.HashMap;
import java.util.Random;

class JogoView extends SurfaceView implements Runnable {

    private Thread gameThread = null;
    private SurfaceHolder ourHolder;

    private volatile boolean playing;

    private Canvas canvas;
    private Paint paint;
    private long fps;
    private Random random;
    private int pontuacao;

    private int contadorObjetosRenovados = 0;

    private long timeThisFrame;

    private Bitmap bitmapBackground;

    private Resources resources;
    private Context contexto;

    /*
    AREA PARA TESTES
     */
    PaintCartaJogo carta;

    public JogoView(Context context) {
        super(context);

        contexto = context;
        pontuacao = 0;

        ourHolder = getHolder();
        paint = new Paint();
        random = new Random();
        resources = context.getResources();

        bitmapBackground = BitmapFactory.decodeResource(this.getResources(), R.drawable.background);
        carta = new PaintCartaJogo(new Carta(), resources);
        playing = true;
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
            carta.paint(canvas, paint);
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