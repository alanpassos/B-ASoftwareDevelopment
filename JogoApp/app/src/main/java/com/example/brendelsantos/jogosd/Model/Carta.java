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


    private Long id;
    private String descricao;
    private String nome;
    private String tipo;
    private Long valorPoder;
    private Long valorVidaTotal;;
    private boolean principal;
    private String carta;
    private boolean ativo;

    private String imagem;
    private Bitmap imagemBitmapCarta;
    private Bitmap imagemCartaFundo;
    private Bitmap imagemCarta;
    private boolean isEscondida;
    private Point posicaoPontoCarta;
    private int posicaoCarta;

    Baralho baralho = new Baralho();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getValorPoder() {
        return valorPoder;
    }

    public void setValorPoder(Long valorPoder) {
        this.valorPoder = valorPoder;
    }

    public Long getValorVidaTotal() {
        return valorVidaTotal;
    }

    public void setValorVidaTotal(Long valorVidaTotal) {
        this.valorVidaTotal = valorVidaTotal;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public String getCarta() {
        return carta;
    }

    public void setCarta(String carta) {
        this.carta = carta;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Bitmap getImagemCarta() {
        return imagemCarta;
    }

    public void setImagemCarta(Bitmap imagemCarta) {
        this.imagemCarta = imagemCarta;
    }

    public Point getPosicaoPontoCarta() {
        return posicaoPontoCarta;
    }

    public void setPosicaoPontoCarta(Point posicaoPontoCarta) {
        this.posicaoPontoCarta = posicaoPontoCarta;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public void setBaralho(Baralho baralho) {
        this.baralho = baralho;
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
