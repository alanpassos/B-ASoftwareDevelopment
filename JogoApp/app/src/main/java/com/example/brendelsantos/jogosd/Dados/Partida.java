package com.example.brendelsantos.jogosd.Dados;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.brendelsantos.jogosd.Activity.JogoActivity;
import com.example.brendelsantos.jogosd.Model.Carta;
import com.example.brendelsantos.jogosd.R;

import java.util.ArrayList;

/**
 * Created by Brendel Santos on 11/05/2016.
 */
public class Partida {

    public static final int NUMERO_MAXIMO_CARTAS = 6;

    ArrayList<Carta> baralho;

    ArrayList<Carta> cartasJogador;
    Resources resources;

    public Partida(Resources resources) {
        this.baralho = new ArrayList<>();
        this.cartasJogador = new ArrayList<>();
        this.resources = resources;
    }

    public ArrayList<Carta> getBaralho() {
        return baralho;
    }

    public void setBaralho(ArrayList<Carta> baralho) {
        this.baralho = baralho;
    }

    public ArrayList<Carta> getCartasJogador() {
        return cartasJogador;
    }

    public void setCartasJogador(ArrayList<Carta> cartasJogador) {
        this.cartasJogador = cartasJogador;
    }

    public void iniciaBaralho(long idBaralho) {
        for (long i = idBaralho; i <= idBaralho + NUMERO_MAXIMO_CARTAS; i++) {
            Carta carta = new Carta();
            String idImagemLocal = "_" + Long.toString(i);
            int identificadorRes = resources.getIdentifier(idImagemLocal, "drawable", JogoActivity.PACKAGE_NAME);
            carta.setImagemBitmapCarta(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, identificadorRes), 140, 186, false));
            carta.setIdCarta(identificadorRes);
            baralho.add(carta);
        }
    }
    public void adicionaCartaJogador(long id, int posicao) {
        Carta carta = new Carta();
        String idImagemLocal = "_" + Long.toString(id);
        int identificadorRes = resources.getIdentifier(idImagemLocal, "drawable", JogoActivity.PACKAGE_NAME);
        carta.setImagemCartaFundo(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.fundo_carta), 140, 186, false));
        carta.setImagemBitmapCarta(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, identificadorRes), 140, 186, false));
        carta.setIsEscondida(true);
        carta.setPosicao(posicao);
        cartasJogador.add(carta);
    }

    public void paintCartas(Canvas canvas, Paint paint) {
        for (Carta carta: cartasJogador) {
            carta.paint(canvas, paint);
        }
    }
}
