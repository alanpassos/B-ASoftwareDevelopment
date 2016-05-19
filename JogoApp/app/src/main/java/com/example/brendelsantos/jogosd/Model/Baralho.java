package com.example.brendelsantos.jogosd.Model;

/**
 * Created by Brendel Santos on 08/05/2016.
 */
public class Baralho {
    private int idJJogador;
    private int idCarta;
    private Jogador jogador;
    private Carta carta;

    public int getIdJJogador() {
        return idJJogador;
    }

    public void setIdJJogador(int idJJogador) {
        this.idJJogador = idJJogador;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }
}
