package com.example.brendelsantos.jogosd.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.brendelsantos.jogosd.Componentes.BotaoCarta;
import com.example.brendelsantos.jogosd.Dados.Partida;
import com.example.brendelsantos.jogosd.Dados.Sessao;
import com.example.brendelsantos.jogosd.R;
import com.example.brendelsantos.jogosd.Tasks.ClienteTask;
import com.example.brendelsantos.jogosd.Tasks.SocketServerThread;
import com.example.brendelsantos.jogosd.Util.Random;

public class JogoActivity extends AppCompatActivity {
    public static String PACKAGE_NAME;

    private View view;
    private RelativeLayout layoutBotoes;
    private JogoView jogoView;
    private Partida partida;
    private Sessao sessao;
    private SocketServerThread serverSocket;
    private BotaoCarta[] botoesCarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PACKAGE_NAME = getApplicationContext().getPackageName();

        inicializaObjetos();
        final LinearLayout game = (LinearLayout) view.findViewById(R.id.linearLayoutGame);
        game.addView(jogoView);
        setContentView(view);
        RelativeLayout areaCartas = (RelativeLayout) view.findViewById(R.id.linearLayoutBotoes);
        areaCartas.setBackgroundResource(R.drawable.area_cartas);
    }

    public void inicializaObjetos(){
        view = getLayoutInflater().inflate(R.layout.activity_jogo, null);
        layoutBotoes = (RelativeLayout) view.findViewById(R.id.linearLayoutBotoes);
        partida = new Partida(getResources());
        partida.iniciaBaralho(1);
        jogoView = new JogoView(this, partida);

        botoesCarta = new BotaoCarta[5];

        sessao = Sessao.getInstance();
        serverSocket =  new SocketServerThread(this);
        Thread socketServerThread = new Thread(serverSocket);
        socketServerThread.start();

        for (int i = 0; i < botoesCarta.length; i++) {
            int indiceRandomCarta = Random.randInt(0, partida.getBaralho().size() - 1);
            String idBotao = "botao_carta_" + Integer.toString(i);
            int identificadorBotaoRes = getResources().getIdentifier(idBotao, "id", PACKAGE_NAME);

            botoesCarta[i] = (BotaoCarta) layoutBotoes.findViewById(identificadorBotaoRes);
            botoesCarta[i].setCarta(partida.getBaralho().get(indiceRandomCarta));
        }

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
