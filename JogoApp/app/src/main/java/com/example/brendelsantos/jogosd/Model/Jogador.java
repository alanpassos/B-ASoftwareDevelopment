package com.example.brendelsantos.jogosd.Model;

import android.graphics.Bitmap;

/**
 * Created by Brendel Santos on 08/05/2016.
 */
public class Jogador {
    private int idJogador;
    private int idUsuario;
    private String nome;
    private String descricao;
    private String imagem;
    private Bitmap imagemBitmap;
    private int nivel;
    private int quantidadeVitorias;
    private int quantidadeDerrotas;
    private boolean ativo;

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Bitmap getImagemBitmap() {
        return imagemBitmap;
    }

    public void setImagemBitmap(Bitmap imagemBitmap) {
        this.imagemBitmap = imagemBitmap;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getQuantidadeVitorias() {
        return quantidadeVitorias;
    }

    public void setQuantidadeVitorias(int quantidadeVitorias) {
        this.quantidadeVitorias = quantidadeVitorias;
    }

    public int getQuantidadeDerrotas() {
        return quantidadeDerrotas;
    }

    public void setQuantidadeDerrotas(int quantidadeDerrotas) {
        this.quantidadeDerrotas = quantidadeDerrotas;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
