package com.example.brendelsantos.jogosd.Dados;

/**
 * Created by Brendel Santos on 11/05/2016.
 */
public class Sessao {
    private static Sessao ourInstance = new Sessao();

    public static Sessao getInstance() {
        return ourInstance;
    }

    private Sessao() {
    }
}
