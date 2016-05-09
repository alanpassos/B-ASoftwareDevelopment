package com.example.brendelsantos.jogosd.Model;

/**
 * Created by Brendel Santos on 08/05/2016.
 */
public class CartaBase extends Carta {
    private String caracteristica;
    private int vida;

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
