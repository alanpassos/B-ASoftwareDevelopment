package com.example.brendelsantos.jogosd.Tasks;

/**
 * Created by Brendel Santos on 10/05/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.brendelsantos.jogosd.Activity.JogoActivity;
import com.example.brendelsantos.jogosd.Componentes.PaintCartaJogo;
import com.example.brendelsantos.jogosd.Dados.Partida;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerThread extends Thread {
    private ServerSocket serverSocket;
    private int SocketServerPORT;
    private int count = 0;
    private Activity activity;
    private Context contexto;
    private String[] mensagemRecebida;
    private Partida partida;

    public SocketServerThread(Activity activity, int porta) {
        this.SocketServerPORT = porta;
        this.activity = activity;
        this.contexto = activity.getApplicationContext();
    }

    public SocketServerThread(Activity activity, int porta, Partida partida) {
        this.SocketServerPORT = porta;
        this.activity = activity;
        this.contexto = activity.getApplicationContext();
        this.partida = partida;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(SocketServerPORT);

            while (true) {
                final Socket socket = serverSocket.accept();
                count++;
                final InetAddress ip =  socket.getInetAddress();
                final int porta = socket.getPort();

                BufferedReader entradaDoCliente = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                mensagemRecebida = entradaDoCliente.readLine().split("#");

                activity.runOnUiThread(new Runnable() {

                   @Override
                   public void run() {
                       Toast.makeText(contexto, "Contador #" + count + " Ip Cliente: " + ip
                               + ":" +  porta + "\n", Toast.LENGTH_SHORT).show();
                   }
                });

                if (mensagemRecebida[0].equals("INICIOJOGO")) {
                    JogoActivity.IP = ip.toString().replace("/", "");
                    Intent intent = new Intent(activity, JogoActivity.class);
                    activity.startActivity(intent);
                    break;
                }

                if (mensagemRecebida.length > 1 && partida != null) {
                    partida.adicionaCartaAdversario(Long.valueOf(mensagemRecebida[0]));
                }

                //activity.getPartida().adicionaCartaAdversario(Long.valueOf(mensagemRecebida[0]), Integer.valueOf(mensagemRecebida[1]));

                SocketServerRespostaThread socketServerRespostaThread = new SocketServerRespostaThread(
                        socket, count);
                socketServerRespostaThread.run();

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}