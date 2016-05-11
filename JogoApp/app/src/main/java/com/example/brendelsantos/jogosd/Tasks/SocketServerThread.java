package com.example.brendelsantos.jogosd.Tasks;

/**
 * Created by Brendel Santos on 10/05/2016.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerThread extends Thread {
    ServerSocket serverSocket;
    static final int SocketServerPORT = 8080;
    int count = 0;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(SocketServerPORT);

            while (true) {
                Socket socket = serverSocket.accept();
                count++;
                //message += "#" + count + " from " + socket.getInetAddress()
                //        + ":" + socket.getPort() + "\n";

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