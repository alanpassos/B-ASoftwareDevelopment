package com.example.brendelsantos.jogosd.Tasks;

/**
 * Created by Brendel Santos on 10/05/2016.
 */
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class SocketServerRespostaThread extends Thread {

    private Socket hostThreadSocket;
    int cnt;

    SocketServerRespostaThread(Socket socket, int c) {
        hostThreadSocket = socket;
        cnt = c;
    }

    @Override
    public void run() {
        OutputStream outputStream;
        String msgReply = "Hello from Android, you are #" + cnt;

        try {
            outputStream = hostThreadSocket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            printStream.print(msgReply);
            printStream.close();

           // message += "replayed: " + msgReply + "\n";

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //message += "Something wrong! " + e.toString() + "\n";
        }

    }

}
