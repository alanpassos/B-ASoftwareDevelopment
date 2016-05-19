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
    public int posicaoCartaJogadorAtual;
    public int posicaoCartaAdversarioAtual;
    ArrayList<Carta> baralhoJogador;
    ArrayList<Carta> baralhoAdversario;

    ArrayList<Carta> cartasJogador;
    ArrayList<Carta> cartasAdversario;

    Resources resources;

    public Partida(Resources resources) {
        this.baralhoJogador = new ArrayList<>();
        this.baralhoAdversario = new ArrayList<>();
        this.cartasJogador = new ArrayList<>();
        this.cartasAdversario = new ArrayList<>();
        this.resources = resources;
        this.posicaoCartaJogadorAtual = 2;
        this.posicaoCartaAdversarioAtual = 6;
    }

    public ArrayList<Carta> getBaralhoJogador() {
        return baralhoJogador;
    }

    public void setBaralhoJogador(ArrayList<Carta> baralhoJogador) {
        this.baralhoJogador = baralhoJogador;
    }


    public ArrayList<Carta> getCartasJogador() {
        return cartasJogador;
    }

    public void setCartasJogador(ArrayList<Carta> cartasJogador) {
        this.cartasJogador = cartasJogador;
    }

    public void iniciaBaralho(long idBaralho, boolean isJogador) {
        for (long i = idBaralho; i <= idBaralho + NUMERO_MAXIMO_CARTAS; i++) {
            Carta carta = new Carta();
            String idImagemLocal = "_" + Long.toString(i);
            int identificadorRes = resources.getIdentifier(idImagemLocal, "drawable", JogoActivity.PACKAGE_NAME);
            carta.setImagemBitmapCarta(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, identificadorRes), 140, 186, false));
            carta.setImagemCartaFundo(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.fundo_carta), 140, 186, false));
            carta.setIdCarta(i);
            if (isJogador)
                baralhoJogador.add(carta);
            else
                baralhoAdversario.add(carta);
        }
    }
    public void adicionaCartaJogador(Carta carta) {
        carta.setPosicao(posicaoCartaJogadorAtual);
        carta.setIsEscondida(false);
        cartasJogador.add(carta);
    }

    public void adicionaCartaAdversario(long idCarta, int posicao) {
        posicaoCartaAdversarioAtual = posicao + 4;
        Carta carta = buscaCartaBaralho(idCarta);
        carta.setPosicao(posicaoCartaAdversarioAtual);
        carta.setIsEscondida(false);
        cartasAdversario.add(carta);
    }

    public Carta buscaCartaBaralho(long idCarta) {
        Carta _carta = null;
        for (Carta carta: baralhoAdversario) {
            if (carta.getIdCarta() == idCarta) {
                _carta = carta;
            }
        }
        return _carta;
    }
    public void paintCartas(Canvas canvas, Paint paint) {
        for (int i = 0; i < cartasJogador.size(); i++) {
            cartasJogador.get(i).paint(canvas, paint);
        }
        for (int i = 0; i < cartasAdversario.size(); i++) {
            cartasAdversario.get(i).paint(canvas, paint);
        }
    }
}
