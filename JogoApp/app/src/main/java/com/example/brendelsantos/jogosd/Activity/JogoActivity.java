package com.example.brendelsantos.jogosd.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.brendelsantos.jogosd.Componentes.BotaoCarta;
import com.example.brendelsantos.jogosd.Dados.Partida;
import com.example.brendelsantos.jogosd.Dados.Sessao;
import com.example.brendelsantos.jogosd.R;
import com.example.brendelsantos.jogosd.Tasks.ClienteTask;
import com.example.brendelsantos.jogosd.Tasks.SocketServerThread;
import com.example.brendelsantos.jogosd.Util.Random;

import java.util.ArrayList;

public class JogoActivity extends AppCompatActivity {
    public static String PACKAGE_NAME;
    public static String IP;
    private View view;
    private RelativeLayout layoutBotoes;
    private JogoView jogoView;
    private Partida partida;
    private Sessao sessao;
    private SocketServerThread serverSocket;
    private BotaoCarta[] botoesCarta;
    private int indiceBaralho;

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
        Intent intent = getIntent();
        String ipMain = intent.getStringExtra(MainActivity.JOGADOR_HOST);
        if (ipMain != null) {
            IP = ipMain;

            ClienteTask myClientTask = new ClienteTask(
                    JogoActivity.IP,
                    8080, "INICIOJOGO");
            myClientTask.execute();
        }

        view = getLayoutInflater().inflate(R.layout.activity_jogo, null);
        layoutBotoes = (RelativeLayout) view.findViewById(R.id.linearLayoutBotoes);
        partida = new Partida(getResources());
        partida.iniciaBaralhos();

        jogoView = new JogoView(this, partida, this);

        botoesCarta = new BotaoCarta[5];

        sessao = Sessao.getInstance();
        serverSocket =  new SocketServerThread(this, 8181, partida);
        Thread socketServerThread = new Thread(serverSocket);
        socketServerThread.start();
        int indiceRandomCarta = -1;
        ArrayList<Integer> indicesSorteados = new ArrayList<>();
        for (int i = 0; i < botoesCarta.length; i++) {
            indiceRandomCarta = Random.randInt(1, partida.getBaralhoUm().size() - 1);

            while (indicesSorteados.contains(indiceRandomCarta)) {
                indiceRandomCarta = Random.randInt(1, partida.getBaralhoUm().size() - 1);
            }
            indicesSorteados.add(indiceRandomCarta);

            String idBotao = "botao_carta_" + Integer.toString(i);
            int identificadorBotaoRes = getResources().getIdentifier(idBotao, "id", PACKAGE_NAME);

            botoesCarta[i] = (BotaoCarta) layoutBotoes.findViewById(identificadorBotaoRes);

            if (MainActivity.ID_JOGADOR % 2 == 0) {
                botoesCarta[i].setCarta(partida.getBaralhoUm().get(indiceRandomCarta));
            } else {
                botoesCarta[i].setCarta(partida.getBaralhoDois().get(indiceRandomCarta));
            }
            botoesCarta[i].setPartida(partida);
        }

    }

    public Partida getPartida() {
        return partida;
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
