package com.example.brendelsantos.jogosd.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.brendelsantos.jogosd.Componentes.PosicoesCartas;

/**
 * Created by Brendel Santos on 08/05/2016.
 */
public class Carta {
    private int idCarta;
    private String imagem;
    private Bitmap imagemBitmapCarta;
    private Bitmap imagemCartaFundo;
    private Bitmap imagemCarta;
    private boolean isEscondida;
    private String nome;
    private String descricao;
    private Point posicaoCarta;

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
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

    public void setPosicao(int numeroPosicao) {
        if (numeroPosicao == 1) {
            posicaoCarta = PosicoesCartas.JOGADOR_BASE;
        } else if (numeroPosicao == 2) {
            posicaoCarta = PosicoesCartas.JOGADOR_PRIMEIRA;
        } else if (numeroPosicao == 3) {
            posicaoCarta = PosicoesCartas.JOGADOR_SEGUNDA;
        } else if (numeroPosicao == 4) {
            posicaoCarta = PosicoesCartas.JOGADOR_TERCEIRA;
        } else if (numeroPosicao == 5) {
            posicaoCarta = PosicoesCartas.ADVERSARIO_BASE;
        } else if (numeroPosicao == 6) {
            posicaoCarta = PosicoesCartas.ADVERSARIO_PRIMEIRA;
        } else if (numeroPosicao == 7) {
            posicaoCarta = PosicoesCartas.ADVERSARIO_SEGUNDA;
        } else if (numeroPosicao == 8) {
            posicaoCarta = PosicoesCartas.ADVERSARIO_TERCEIRA;
        }
    }

    public void paint(Canvas canvas, Paint paint) {
        canvas.drawBitmap(imagemCarta, posicaoCarta.x, posicaoCarta.y, paint);

    }

}
