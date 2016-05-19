package com.example.brendelsantos.jogosd.Dados;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.brendelsantos.jogosd.Activity.JogoActivity;
import com.example.brendelsantos.jogosd.Activity.MainActivity;
import com.example.brendelsantos.jogosd.Model.Carta;
import com.example.brendelsantos.jogosd.R;
import com.example.brendelsantos.jogosd.Tasks.ClienteTask;
import com.example.brendelsantos.jogosd.Util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Brendel Santos on 11/05/2016.
 */
public class Partida {

    public static final int NUMERO_MAXIMO_CARTAS = 6;
    public int posicaoCartaJogadorAtual;
    public int posicaoCartaAdversarioAtual;
    List<Carta> baralhoUm;
    List<Carta> baralhoDois;

    ArrayList<Carta> cartasJogador;
    ArrayList<Carta> cartasAdversario;

    JsonUtil jsonUtil;
    Resources resources;

    boolean acabouRodadaAdversario;
    boolean acabouRodadaJogador;

    public Partida(Resources resources) {

        jsonUtil = new JsonUtil();
        String json = "";
        ClienteTask myClientTask = new ClienteTask(
                MainActivity.IP_SERVER,
                6783, "" + "baralho#porid#1", json);
        try {
            json = myClientTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        acabouRodadaAdversario = false;
        acabouRodadaJogador = false;

        this.baralhoUm = jsonUtil.gerarLista(Carta.class, json);

        json = "";
        myClientTask = new ClienteTask(
                MainActivity.IP_SERVER,
                6783, "" + "baralho#porid#2", json);
        try {
            json = myClientTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        this.baralhoDois =jsonUtil.gerarLista(Carta.class, json);
        this.cartasJogador = new ArrayList<>();
        this.cartasAdversario = new ArrayList<>();
        this.resources = resources;
        this.posicaoCartaJogadorAtual = 2;
        this.posicaoCartaAdversarioAtual = 6;
    }

    public List<Carta> getBaralhoUm() {
        return baralhoUm;
    }

    public void setBaralhoUm(ArrayList<Carta> baralhoUm) {
        this.baralhoUm = baralhoUm;
    }

    public List<Carta> getBaralhoDois() {
        return baralhoDois;
    }

    public void setBaralhoDois(List<Carta> baralhoDois) {
        this.baralhoDois = baralhoDois;
    }

    public ArrayList<Carta> getCartasJogador() {
        return cartasJogador;
    }

    public ArrayList<Carta> getCartasAdversario() {
        return cartasAdversario;
    }

    public boolean isAcabouRodadaAdversario() {
        return acabouRodadaAdversario;
    }

    public void setAcabouRodadaAdversario(boolean acabouRodadaAdversario) {
        this.acabouRodadaAdversario = acabouRodadaAdversario;
    }

    public boolean isAcabouRodadaJogador() {
        return acabouRodadaJogador;
    }

    public void setAcabouRodadaJogador(boolean acabouRodadaJogador) {
        this.acabouRodadaJogador = acabouRodadaJogador;
    }

    public void setCartasJogador(ArrayList<Carta> cartasJogador) {
        this.cartasJogador = cartasJogador;
    }



    public void iniciaBaralhos() {
        for (int i = 0; i < baralhoUm.size(); i++) {
            String idImagemLocal = "_" + Long.toString(baralhoUm.get(i).getId());
            int identificadorRes = resources.getIdentifier(idImagemLocal, "drawable", JogoActivity.PACKAGE_NAME);
            baralhoUm.get(i).setImagemBitmapCarta(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, identificadorRes), 140, 186, false));
            baralhoUm.get(i).setImagemCartaFundo(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.fundo_carta), 140, 186, false));
        }

        for (int i = 0; i < baralhoDois.size(); i++) {
            String idImagemLocal = "_" + Long.toString(baralhoDois.get(i).getId());
            int identificadorRes = resources.getIdentifier(idImagemLocal, "drawable", JogoActivity.PACKAGE_NAME);
            baralhoDois.get(i).setImagemBitmapCarta(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, identificadorRes), 140, 186, false));
            baralhoDois.get(i).setImagemCartaFundo(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.fundo_carta), 140, 186, false));
        }

        if (MainActivity.ID_JOGADOR % 2 == 0) {
            baralhoUm.get(0).setPosicao(1);
            baralhoUm.get(0).setIsEscondida(false);
            cartasJogador.add(baralhoUm.get(0));
            baralhoDois.get(0).setPosicao(5);
            baralhoDois.get(0).setIsEscondida(false);
            cartasAdversario.add(baralhoDois.get(0));
        } else {
            baralhoUm.get(0).setPosicao(5);
            baralhoUm.get(0).setIsEscondida(false);
            cartasJogador.add(baralhoUm.get(0));
            baralhoDois.get(0).setPosicao(1);
            baralhoDois.get(0).setIsEscondida(false);
            cartasAdversario.add(baralhoDois.get(0));
        }


    }
    public void adicionaCartaJogador(Carta carta) {
        if (!acabouRodadaJogador && !cartasJogador.contains(carta)) {
            carta.setPosicao(posicaoCartaJogadorAtual);
            carta.setIsEscondida(false);
            cartasJogador.add(carta);
            posicaoCartaJogadorAtual++;
        }


        if (posicaoCartaJogadorAtual == 5) {
            acabouRodadaJogador = true;
//            cartasJogador.remove(1);
//            cartasJogador.remove(2);
//            cartasJogador.remove(3);
            posicaoCartaJogadorAtual = 2;
        }
    }

    public void adicionaCartaAdversario(long idCarta) {
        Carta carta = buscaCartaBaralho(idCarta);
        if (!acabouRodadaAdversario && !cartasAdversario.contains(carta)) {
            carta.setPosicao(posicaoCartaAdversarioAtual);
            carta.setIsEscondida(true);
            cartasAdversario.add(carta);
            posicaoCartaAdversarioAtual++;
        }


        if (posicaoCartaAdversarioAtual == 9) {
            acabouRodadaAdversario = true;

            posicaoCartaAdversarioAtual = 6;
//            cartasAdversario.remove(1);
//            cartasAdversario.remove(2);
//            cartasAdversario.remove(3);
        }
    }

    public Carta buscaCartaBaralho(long idCarta) {
        Carta _carta = null;
        for (Carta carta: baralhoDois) {
            if (carta.getId() == idCarta) {
                _carta = carta;
            }
        }
        if (_carta == null) {
            for (Carta carta: baralhoUm) {
                if (carta.getId() == idCarta) {
                    _carta = carta;
                }
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
