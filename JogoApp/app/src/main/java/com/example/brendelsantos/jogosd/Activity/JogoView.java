package com.example.brendelsantos.jogosd.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.brendelsantos.jogosd.Componentes.PaintCartaJogo;
import com.example.brendelsantos.jogosd.Dados.Partida;
import com.example.brendelsantos.jogosd.Model.Carta;
import com.example.brendelsantos.jogosd.R;

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
    Partida partida;
    private int contador = 0;

    public JogoView(Context context, Partida partida) {
        super(context);

        this.contexto = context;
        this.pontuacao = 0;
        Toast.makeText(contexto, "Teste", Toast.LENGTH_SHORT).show();

        this.ourHolder = getHolder();
        this.paint = new Paint();
        this.random = new Random();
        this. resources = context.getResources();

        this.bitmapBackground = BitmapFactory.decodeResource(this.getResources(), R.drawable.background);
        this.partida = partida;
        this.partida.adicionaCartaJogador(1, 1);
        this.partida.adicionaCartaJogador(2, 2);
        this.partida.adicionaCartaJogador(7, 3);
        this.partida.adicionaCartaJogador(6, 4);
        this.partida.adicionaCartaJogador(8, 5);
        this.partida.adicionaCartaJogador(9, 6);
        this.partida.adicionaCartaJogador(13, 7);
        this.partida.adicionaCartaJogador(14, 8);

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
            partida.paintCartas(canvas, paint);
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