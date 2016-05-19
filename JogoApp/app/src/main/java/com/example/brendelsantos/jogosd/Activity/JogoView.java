package com.example.brendelsantos.jogosd.Activity;

import android.app.Activity;
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

import com.example.brendelsantos.jogosd.Dados.Partida;
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
    private long hpAdversario;
    private long hpJogador;

    int valorAtaqueAdversario = 0;
    int valorAtaqueJogador = 0;

    private int contadorObjetosRenovados = 0;

    private long timeThisFrame;

    private Bitmap bitmapBackground;

    private Resources resources;
    private Context contexto;
    Activity activity;

    /*
    AREA PARA TESTES
     */
    Partida partida;
    private int contador = 0;

    public JogoView(Context context, Partida partida, Activity activity) {
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
        this.activity = activity;
        hpAdversario = partida.getCartasAdversario().get(0).getValorVidaTotal();
        hpJogador = partida.getCartasJogador().get(0).getValorVidaTotal();
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
        if (partida.isAcabouRodadaAdversario() && partida.isAcabouRodadaJogador()) {


            for (int i = 0; i < partida.getCartasAdversario().size(); i++) {
                partida.getCartasAdversario().get(i).setIsEscondida(false);
                valorAtaqueAdversario +=  partida.getCartasAdversario().get(i).getValorPoder();
            }
            partida.getCartasAdversario().remove(1);
            partida.getCartasAdversario().remove(2);
            partida.getCartasAdversario().remove(3);

            for (int i = 0; i < partida.getCartasJogador().size(); i++) {
                valorAtaqueJogador += partida.getCartasJogador().get(i).getValorPoder();
                partida.getCartasJogador().remove(i);
            }

            partida.getCartasJogador().remove(1);
            partida.getCartasJogador().remove(2);
            partida.getCartasJogador().remove(3);

            final String dados = "Ataque Adversário: " + valorAtaqueAdversario + "\nAtaque Jogador: " + valorAtaqueJogador + "\n";
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(contexto, dados, Toast.LENGTH_SHORT).show();
                }
            });
            partida.setAcabouRodadaAdversario(false);
            partida.setAcabouRodadaJogador(false);

            if (valorAtaqueAdversario > valorAtaqueJogador) {
                hpJogador -= (valorAtaqueAdversario - valorAtaqueJogador);
            } else {
                hpAdversario -= (valorAtaqueJogador - valorAtaqueAdversario);
            }

            final String dadosHp = "Hp Adversário: " + hpAdversario + "\nHp Jogador: " + hpJogador + "\n";

            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(contexto, dadosHp, Toast.LENGTH_SHORT).show();
                }
            });

            if (hpAdversario <= 0 ){
                Toast.makeText(contexto, "Você ganhou!", Toast.LENGTH_SHORT).show();
            } else if (hpJogador <= 0)  {
                Toast.makeText(contexto, "Você perdeu!", Toast.LENGTH_SHORT).show();
            }
        }
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