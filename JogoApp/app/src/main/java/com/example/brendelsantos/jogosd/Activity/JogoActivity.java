package com.example.brendelsantos.jogosd.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.brendelsantos.jogosd.R;

public class JogoActivity extends AppCompatActivity {
    private JogoView jogoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.activity_jogo, null);
        final LinearLayout game = (LinearLayout) view.findViewById(R.id.linearLayoutGame);
        jogoView = new JogoView(this);
        game.addView(jogoView);
        setContentView(view);
        RelativeLayout areaCartas = (RelativeLayout) view.findViewById(R.id.linearLayoutBotoes);
        areaCartas.setBackgroundResource(R.drawable.area_cartas);
    }

    @Override
    protected void onResume() {
        super.onResume();
        jogoView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jogoView.pause();
    }

}
