package com.example.brendelsantos.jogosd.Tasks;

/**
 * Created by Brendel Santos on 10/05/2016.
 */

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;

public class ClienteTask extends AsyncTask<Void, Void, Void> {

    private String enderecoDestino;
    private int portaDestino;
    private String mensagemEnviada;
    private String resposta ;
    private OutputStream os;
    private OutputStreamWriter osw;
    BufferedWriter bw;

    public ClienteTask(String addr, int port, String mensagemEnviada){
        this.enderecoDestino = addr;
        this.portaDestino = port;
        this.mensagemEnviada = mensagemEnviada;
        this.resposta = "";
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(enderecoDestino, portaDestino);
            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            String mensagemEnviada = this.mensagemEnviada + "\n";
            bw.write(mensagemEnviada);
            bw.flush();

            ByteArrayOutputStream byteArrayOutputStream =
                    new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();

            while ((bytesRead = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                resposta += byteArrayOutputStream.toString("UTF-8");
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resposta = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resposta = "IOException: " + e.toString();
        }finally{
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        //textResposta.setText(resposta);
        super.onPostExecute(result);
    }

}