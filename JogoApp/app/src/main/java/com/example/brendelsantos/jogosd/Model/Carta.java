package com.example.brendelsantos.jogosd.Model;

import android.graphics.Bitmap;

/**
 * Created by Brendel Santos on 08/05/2016.
 */
public class Carta {
    private int idCarta;
    private String imagem;
    private Bitmap imagemBitmap;
    private String nome;
    private String descricao;

    public Bitmap getImagemBitmap() {
        return imagemBitmap;
    }

    public void setImagemBitmap(Bitmap imagemBitmap) {
        this.imagemBitmap = imagemBitmap;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
