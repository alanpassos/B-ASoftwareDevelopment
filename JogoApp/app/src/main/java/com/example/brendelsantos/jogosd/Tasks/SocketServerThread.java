package com.example.brendelsantos.jogosd.Tasks;

/**
 * Created by Brendel Santos on 10/05/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.brendelsantos.jogosd.Activity.JogoActivity;
import com.example.brendelsantos.jogosd.Componentes.PaintCartaJogo;

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
    private static final int SocketServerPORT = 8080;
    private int count = 0;
    private JogoActivity activity;
    private Context contexto;
    private String[] mensagemRecebida;

    public SocketServerThread(JogoActivity activity) {
        this.activity = activity;
        this.contexto = activity.getApplicationContext();
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
                       Toast.makeText(contexto, "#" + count + " from " + ip
                               + ":" +  porta +  "Mensagem: " + mensagemRecebida + "\n", Toast.LENGTH_SHORT).show();
                   }
                });

                activity.getPartida().adicionaCartaAdversario(Long.valueOf(mensagemRecebida[0]), Integer.valueOf(mensagemRecebida[1]));

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