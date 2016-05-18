package com.example.brendelsantos.jogosd.Util;

/**
 * Created by Brendel Santos on 18/05/2016.
 */
public class Random {
    public static int randInt(int min, int max) {
        java.util.Random rand =  new java.util.Random();
        int randomNumero = rand.nextInt((max - min) + 1) + min;
        return randomNumero;
    }
}
