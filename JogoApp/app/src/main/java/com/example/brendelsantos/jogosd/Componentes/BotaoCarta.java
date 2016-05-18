package com.example.brendelsantos.jogosd.Componentes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import com.example.brendelsantos.jogosd.Model.Carta;
import com.example.brendelsantos.jogosd.Tasks.ClienteTask;

/**
 * Created by Brendel Santos on 18/05/2016.
 */
public class BotaoCarta extends ImageButton implements View.OnClickListener {

    private Carta carta;

    public BotaoCarta(Context context) {
        super(context);
        init();
    }

    public BotaoCarta(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BotaoCarta(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
        setImage();
    }

    private void setImage(){
        this.setImageBitmap(carta.getImagemBitmapCarta());
    }
    private void init(){
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ClienteTask myClientTask = new ClienteTask(
                "192.168.1.5",
                8080, "" + carta.getIdCarta());
        myClientTask.execute();
    }
}
