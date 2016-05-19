package com.example.brendelsantos.jogosd.Model;

/**
 * Created by Brendel Santos on 19/05/2016.
 */
public enum TipoBaralho {

    AGRESSIVO("Agressivo"),
    EQUILIBRADO("Equilibrado"),
    DEFENSIVO("Defensivo");

    private String descricao;

    private TipoBaralho(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
