package com.example.brendelsantos.jogosd.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.brendelsantos.jogosd.Componentes.PosicoesCartas;

import java.util.Date;

/**
 * Created by Brendel Santos on 08/05/2016.
 */
public class Carta {

    private Long idCarta;
    private Long valorVidaTotal;;

    private boolean principal;
    private String carta;
    private Date dataCadastro;
    private String imagem;
    private Bitmap imagemBitmapCarta;
    private Bitmap imagemCartaFundo;
    private Bitmap imagemCarta;
    private boolean isEscondida;
    private String nome;
    private String descricao;
    private Point posicaoPontoCarta;
    private int posicaoCarta;
    private boolean ativo;

    public Long getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(Long idCarta) {
        this.idCarta = idCarta;
    }

    public Bitmap getImagemBitmapCarta() {
        return imagemBitmapCarta;
    }

    public void setImagemBitmapCarta(Bitmap imagemBitmapCarta) {
        this.imagemBitmapCarta = imagemBitmapCarta;
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

    public Bitmap getImagemCartaFundo() {
        return imagemCartaFundo;
    }

    public void setImagemCartaFundo(Bitmap imagemCartaFundo) {
        this.imagemCartaFundo = imagemCartaFundo;
    }

    public boolean isEscondida() {
        return isEscondida;
    }

    public void setIsEscondida(boolean isEscondida) {
        this.isEscondida = isEscondida;
        if (isEscondida)
            imagemCarta = imagemCartaFundo;
        else
            imagemCarta = imagemBitmapCarta;
    }

    public int getPosicaoCarta() {
        return posicaoCarta;
    }

    public void setPosicaoCarta(int posicaoCarta) {
        this.posicaoCarta = posicaoCarta;
    }

    public void setPosicao(int numeroPosicao) {
        posicaoCarta = numeroPosicao;
        if (numeroPosicao == 1) {
            posicaoPontoCarta = PosicoesCartas.JOGADOR_BASE;
        } else if (numeroPosicao == 2) {
            posicaoPontoCarta = PosicoesCartas.JOGADOR_PRIMEIRA;
        } else if (numeroPosicao == 3) {
            posicaoPontoCarta = PosicoesCartas.JOGADOR_SEGUNDA;
        } else if (numeroPosicao == 4) {
            posicaoPontoCarta = PosicoesCartas.JOGADOR_TERCEIRA;
        } else if (numeroPosicao == 5) {
            posicaoPontoCarta = PosicoesCartas.ADVERSARIO_BASE;
        } else if (numeroPosicao == 6) {
            posicaoPontoCarta = PosicoesCartas.ADVERSARIO_PRIMEIRA;
        } else if (numeroPosicao == 7) {
            posicaoPontoCarta = PosicoesCartas.ADVERSARIO_SEGUNDA;
        } else if (numeroPosicao == 8) {
            posicaoPontoCarta = PosicoesCartas.ADVERSARIO_TERCEIRA;
        }
    }

    public void paint(Canvas canvas, Paint paint) {
        canvas.drawBitmap(imagemCarta, posicaoPontoCarta.x, posicaoPontoCarta.y, paint);

    }

}
