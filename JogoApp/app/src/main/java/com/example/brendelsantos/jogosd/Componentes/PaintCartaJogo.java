package com.example.brendelsantos.jogosd.Componentes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.brendelsantos.jogosd.Activity.JogoActivity;
import com.example.brendelsantos.jogosd.Model.Carta;

/**
 * Created by Brendel Santos on 11/05/2016.
 */
public class PaintCartaJogo {
    private Carta carta;
    private Point posicaoCarta;

    public PaintCartaJogo(Carta carta, Resources resources) {
        this.carta = carta;
        String id = "_1";
        int identificadorRes = resources.getIdentifier(id, "drawable", JogoActivity.PACKAGE_NAME);
        this.carta.setImagemBitmapCarta(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, identificadorRes), 140, 186, false));
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
        canvas.drawBitmap(carta.getImagemBitmapCarta(), posicaoCarta.x, posicaoCarta.y, paint);

    }

}
