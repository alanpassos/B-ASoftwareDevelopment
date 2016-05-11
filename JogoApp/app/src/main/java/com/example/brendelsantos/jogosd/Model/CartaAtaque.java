package com.example.brendelsantos.jogosd.Model;

/**
 * Created by Brendel Santos on 08/05/2016.
 */
public class CartaAtaque extends Carta {
    private int valorAtaque;
    private int valorDefesa;

    public int getValorAtaque() {
        return valorAtaque;
    }

    public void setValorAtaque(int valorAtaque) {
        this.valorAtaque = valorAtaque;
    }

    public int getValorDefesa() {
        return valorDefesa;
    }

    public void setValorDefesa(int valorDefesa) {
        this.valorDefesa = valorDefesa;
    }
}
